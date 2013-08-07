package com.wy.webprototype.web.controller;

import static com.wy.webprototype.util.AppContextWrapper.get;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wy.webprototype.model.User;
import com.wy.webprototype.service.UserService;

/**
 * Needs to be executed under common-db-jpa-config.xml
 * @author whitman.yang
 *
 */
@Controller
@RequestMapping(value = "/controllers/jpa")
public class JpaController extends BaseController {
	
	@Autowired
	UserService userService;
	
	private EntityManager getEntityMgr() {
		EntityManagerFactory factory = get(EntityManagerFactory.class);
		if (factory != null) {
			return factory.createEntityManager();
		}
		return null;
	}
	
	/**
	 * JPA test - query user
	 * @param userId
	 * @return String
	 */
	@RequestMapping(value = "/testQueryUser", method = RequestMethod.GET)
	@ResponseBody
	public String testQueryUser(@RequestParam(value="userId",required = true) long userId) {
		EntityManager em = getEntityMgr();
		User user = em.find(User.class, userId);
		em.close();
		
		if (user != null) {
			return user.getUserName();
		} else {
			return "null";
		}
	}
	
	/**
	 * JPA test - get user via user service
	 * @param userId
	 * @return String
	 */
	@RequestMapping(value = "/testGetUser", method = RequestMethod.GET)
	@ResponseBody
	public String testGetUser(@RequestParam(value="userId",required = true) long userId) {
		User user = userService.getUserById(userId);
		
		if (user != null) {
			return user.getUserName();
		} else {
			return "null";
		}
	}
	
	/**
	 * JPA test - get user through user name via user service
	 * @param userName
	 * @return userId
	 */
	@RequestMapping(value = "/testGetUserWithName", method = RequestMethod.GET)
	@ResponseBody
	public Long testGetUserWithName(@RequestParam(value="userName",required = true) String userName) {
		User user = userService.getUserByUserName(userName);
		
		if (user != null) {
			return user.getUserId();
		} else {
			return -1L;
		}
	}
	
	/**
	 * JPA test - add user
	 * @param userId
	 * @return String
	 */
	@RequestMapping(value = "/testAddUser", method = RequestMethod.GET)
	@ResponseBody
	public String testAddUser(@RequestParam(value="userId",required = true) long userId) {
		EntityManager em = getEntityMgr();
		User user = new User(userId, "test" + userId);
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
		return "success";
	}
	
	/**
	 * JPA test - insert user via user service (no trans)
	 * @param userId
	 * @return String
	 */
	@RequestMapping(value = "/testInsertUserNoTrans", method = RequestMethod.GET)
	@ResponseBody
	public String testInsertUserNoTrans(@RequestParam(value="userId",required = true) long userId) {
		User user = new User(userId, "test" + userId);
		userService.save(user);
		return "success";
	}
	
	/**
	 * JPA test - insert user via user service (in trans)
	 * @param userId
	 * @return String
	 */
	@RequestMapping(value = "/testInsertUserTrans", method = RequestMethod.GET)
	@ResponseBody
	@Transactional
	public String testInsertUserTrans(@RequestParam(value="userId",required = true) long userId) {
		User user = new User(userId, "test" + userId);
		userService.save(user);
		return "success";
	}

}
