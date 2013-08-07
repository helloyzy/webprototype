package com.wy.webprototype.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wy.webprototype.dao.UserDao;
import com.wy.webprototype.model.User;
import com.wy.webprototype.service.UserService;

@Service("userSvc")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	public User getUserById(long userId) {
		return userDao.getUserByUserId(userId);
	}

	@Override
	public User save(User user) {
		return userDao.save(user);
	}

	@Override
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

}
