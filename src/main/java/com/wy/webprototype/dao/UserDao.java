package com.wy.webprototype.dao;

import org.springframework.data.repository.CrudRepository;

import com.wy.webprototype.model.User;

public interface UserDao extends CrudRepository<User, Long> {
	
	public User getUserByUserId(long userId);
	
	public User getUserByUserName(String userName);

}
