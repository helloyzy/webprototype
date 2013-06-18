package com.wy.webprototype.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wy.webprototype.web.model.WebUser;

@Controller
@RequestMapping(value = "/controllers/simple")
public class SimpleController {
	
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
	
	
	
}
