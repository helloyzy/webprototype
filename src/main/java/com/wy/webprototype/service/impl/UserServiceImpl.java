package com.wy.webprototype.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wy.webprototype.dao.UserDao;
import com.wy.webprototype.model.User;
import com.wy.webprototype.service.UserService;

@Service("userSvc")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	@Override
	@Transactional
	public User save(User user) {
		return userDao.save(user);
	}

}
