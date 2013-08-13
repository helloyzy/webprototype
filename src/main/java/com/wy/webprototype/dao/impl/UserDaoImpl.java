package com.wy.webprototype.dao.impl;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wy.webprototype.dao.UserDao;
import com.wy.webprototype.model.User;

/**
 * This class is used by common-db-config, empty implementation
 * @author whitman.yang
 *
 */
public class UserDaoImpl implements UserDao {

	@Override
	public <S extends User> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> Iterable<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<User> findAll(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends User> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User customizedById(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUserId(long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Query("select u from User u where u.userName = ?")
	public List<User> findByUserName(String firstname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Query("select u from User u where u.userId = :userId")
	public List<User> findByUserId(@Param("userId") long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
