package com.wy.webprototype.service;

import com.wy.webprototype.model.User;

public interface UserService {
	
	public User getUserById(long userId);
	
	public User getUserByUserName(String userName);
	
	public User save(User user);

}
