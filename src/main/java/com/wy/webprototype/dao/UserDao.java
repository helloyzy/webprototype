package com.wy.webprototype.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.wy.webprototype.model.User;

/**
 * Spring data JPA - Dao interface demonstration (only need todeclare interface methods, spring data will build up proxies
 * during start up)
 * @author whitman.yang
 *
 */
public interface UserDao extends CrudRepository<User, Long>, CustomizedUserDao {
	
	public User getUserByUserId(long userId);
	
	public User getUserByUserName(String userName);
	
	@Query("select u from User u where u.userName = ?")
	public List<User> findByUserName(String firstname);
	 
	@Query("select u from User u where u.userId = :userId")
	public List<User> findByUserId(@Param("userId") long userId);

}
