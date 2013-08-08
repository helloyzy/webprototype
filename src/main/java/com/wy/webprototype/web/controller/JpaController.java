package com.wy.webprototype.web.controller;

import static com.wy.webprototype.util.AppContextWrapper.getEntityMgr;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wy.webprototype.dao.UserDao;
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
	
	@Autowired
	UserDao userDao;
	
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
	 * JPA test - get user via user service through customized interface 
	 * @param userId
	 * @return String
	 */
	@RequestMapping(value = "/testGetUserCustomized", method = RequestMethod.GET)
	@ResponseBody
	public String testGetUserCustomized(@RequestParam(value="userId",required = true) long userId) {
		User user = userDao.customizedById(userId);
		
		if (user != null) {
			return user.getUserName();
		} else {
			return "null";
		}
	}
	
	/**
	 * JPA test - get user 
	 * @param userId
	 * @return String
	 */
	@RequestMapping(value = "/testGetUser", method = RequestMethod.GET)
	@ResponseBody
	public String testGetUser(@RequestParam(value="userId",required = true) long userId) {
		User user = userDao.getUserByUserId(userId);
		
		if (user != null) {
			return user.getUserName();
		} else {
			return "null";
		}
	}
	
	/**
	 * JPA test - find user
	 * @param userId
	 * @return String
	 */
	@RequestMapping(value = "/testFindUser", method = RequestMethod.GET)
	@ResponseBody
	public String testFindUser(@RequestParam(value="userId",required = true) long userId) {
		User user = userDao.findByUserId(userId).get(0);
		
		if (user != null) {
			return user.getUserName();
		} else {
			return "null";
		}
	}
	
	/**
	 * JPA test - find user by name
	 * @param userName
	 * @return String
	 */
	@RequestMapping(value = "/testFindUserByName", method = RequestMethod.GET)
	@ResponseBody
	public String testFindUserByName(@RequestParam(value="userName",required = true) String userName) {
		User user = userDao.findByUserName(userName).get(0);
		
		if (user != null) {
			return Long.toString(user.getUserId());
		} else {
			return "null";
		}
	}
	
	/**
	 * JPA test - get user through user name 
	 * @param userName
	 * @return userId
	 */
	@RequestMapping(value = "/testGetUserWithName", method = RequestMethod.GET)
	@ResponseBody
	public Long testGetUserWithName(@RequestParam(value="userName",required = true) String userName) {
		User user = userDao.getUserByUserName(userName);
		
		if (user != null) {
			return user.getUserId();
		} else {
			return -1L;
		}
	}
	
	/**
	 * JPA test - insert user (in trans)
	 * @param userId
	 * @return String
	 */
	@RequestMapping(value = "/testInsertUserTrans", method = RequestMethod.GET)
	@ResponseBody
	public String testInsertUserTrans(@RequestParam(value="userId",required = true) long userId) {
		User user = new User(userId, "test" + userId);
		userService.save(user);
		return "success";
	}

}
