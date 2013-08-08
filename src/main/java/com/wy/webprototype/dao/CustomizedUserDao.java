package com.wy.webprototype.dao;

import com.wy.webprototype.model.User;

/**
 * Spring data JPA - Provide customization point for UserDao to inherit
 * @author whitman.yang
 *
 */
public interface CustomizedUserDao {

	public User customizedById(long userId);
}
