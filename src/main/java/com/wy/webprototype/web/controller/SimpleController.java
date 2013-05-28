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
	
	@RequestMapping(value = "/testInternalVR", method = RequestMethod.GET)
	public String testInternalViewResolvers() {
		return "testInternalVR";
	}
	
	@RequestMapping(value = "/testRSVR", method = RequestMethod.GET)
	public String testRSViewResolvers() {
		return "testRSVR";
	}
	
	@RequestMapping(value = "/testAddUser", method = RequestMethod.GET)
	@ResponseBody
	@Transactional
	public String testAddUser(@RequestParam(value="userId",required = true) Long userId) {
		User user = new User();
		user.setUserId(userId);
		user.setUserName("test" + userId);
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		return "success";
	}
	
	@RequestMapping(value = "/testQueryUser", method = RequestMethod.GET)
	@ResponseBody
	public String testQueryUser(@RequestParam(value="userId",required = true) Long userId) {
		Session session = sessionFactory.openSession();
		User user = (User)session.get(User.class, userId);
		if (user != null) {
			return user.getUserName();
		} else {
			return "null";
		}
	}
	
}
