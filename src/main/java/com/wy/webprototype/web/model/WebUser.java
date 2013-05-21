package com.wy.webprototype.web.model;

public class WebUser {
	
	private String userName;
	
	private int age;
	
	public String getUserName() {
		return userName;
	}

	public int getAge() {
		return age;
	}

	public WebUser(String userName, int age) {
		this.userName = userName;
		this.age = age;
	}

}
