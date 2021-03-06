package com.wy.webprototype.web.controller;

import static com.wy.webprototype.util.AppContextWrapper.get;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wy.webprototype.model.User;

/**
 * Needs to be executed under common-db-config.xml
 * @author whitman.yang
 *
 */
@Controller
@RequestMapping(value = "/controllers/sessionfactory")
public class SessionFactoryController extends BaseController {
	
	private SessionFactory getSessionFactory() {
		return get(SessionFactory.class);
	}
	
	/**
	 * Hibernate test - add user in a transaction
	 * @param userId
	 * @return String
	 */
	@RequestMapping(value = "/testAddUser", method = RequestMethod.GET)
	@ResponseBody
	@Transactional
	public String testAddUser(@RequestParam(value="userId",required = true) long userId) {
		User user = new User();
		user.setUserId(userId);
		user.setUserName("test" + userId);
		// get the current session and thus make @Transactional work
		Session session = getSessionFactory().getCurrentSession();
		session.save(user);
		return "success";
	}
	
	/**
	 * Hibernate test - query user in a new session
	 * @param userId
	 * @return String
	 */
	@RequestMapping(value = "/testQueryUser", method = RequestMethod.GET)
	@ResponseBody
	public String testQueryUser(@RequestParam(value="userId",required = true) long userId) {
		Session session = getSessionFactory().openSession(); // open a new session
		User user = (User)session.get(User.class, userId);
		session.close();
		if (user != null) {
			return user.getUserName();
		} else {
			return "null";
		}
	}
	
	/**
	 * Test for org.hibernate.NonUniqueObjectException: a different object with the same identifier value was already associated with the session
	 * @param userId
	 */
	@RequestMapping(value = "/testNUOE", method = RequestMethod.GET)
	public void testNonUniqueObjectException(@RequestParam(value="userId",required = true) long userId) {
		Session session = getSessionFactory().openSession(); // open a new session
		try {
			session.get(User.class, userId); // first load the user into the first-level cache of the session
			User user2 = new User();
			user2.setUserId(userId);
			user2.setUserName("hello"); 
			session.save(user2); // try modifying the user without merge
		} finally {
			session.close();
		}
	}

}
