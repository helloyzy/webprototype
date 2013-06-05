package com.wy.webprototype.web.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wy.webprototype.model.User;
import com.wy.webprototype.web.model.WebUser;

@Controller
@RequestMapping(value = "/controllers/simple")
public class SimpleController {
	
	@Autowired
	SessionFactory sessionFactory;

	@RequestMapping(value = "/testString", method = RequestMethod.GET)
	@ResponseBody
	public String testString() {
		// simple return a String as the body
		return "hello";
	}
	
	@RequestMapping(value = "/testObject", method = RequestMethod.GET)
	@ResponseBody
	public WebUser testObject() {
		WebUser user = new WebUser("hello", 18);
		return user;
	}
	
	/**
	 * View resolvers test - internal view resolver
	 * @return String
	 */
	@RequestMapping(value = "/testInternalVR", method = RequestMethod.GET)
	public String testInternalViewResolvers() {
		return "testInternalVR";
	}
	
	/**
	 * View resolvers test - resource view resolver
	 * @return String
	 */
	@RequestMapping(value = "/testRSVR", method = RequestMethod.GET)
	public String testRSViewResolvers() {
		return "testRSVR";
	}
	
	/**
	 * Hibernate test - add user in a transaction
	 * @param userId
	 * @return String
	 */
	@RequestMapping(value = "/testAddUser", method = RequestMethod.GET)
	@ResponseBody
	@Transactional
	public String testAddUser(@RequestParam(value="userId",required = true) Long userId) {
		User user = new User();
		user.setUserId(userId);
		user.setUserName("test" + userId);
		// get the current session and thus make @Transactional work
		Session session = sessionFactory.getCurrentSession();
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
	public String testQueryUser(@RequestParam(value="userId",required = true) Long userId) {
		Session session = sessionFactory.openSession(); // open a new session
		User user = (User)session.get(User.class, userId);
		session.close();
		if (user != null) {
			return user.getUserName();
		} else {
			return "null";
		}
	}
	
}
