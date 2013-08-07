package com.wy.webprototype.web.controller;

import static com.wy.webprototype.util.AppContextWrapper.get;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wy.webprototype.model.User;

/**
 * Needs to be executed under common-db-jpa-config.xml
 * @author whitman.yang
 *
 */
@Controller
@RequestMapping(value = "/controllers/jpa")
public class JpaController extends BaseController {
	
	private EntityManager getEntityMgr() {
		EntityManagerFactory factory = get(EntityManagerFactory.class);
		if (factory != null) {
			return factory.createEntityManager();
		}
		return null;
	}
	
	/**
	 * JPA test - query user
	 * @param userId
	 * @return String
	 */
	@RequestMapping(value = "/testQueryUser", method = RequestMethod.GET)
	@ResponseBody
	public String testQueryUser(@RequestParam(value="userId",required = true) long userId) {
		EntityManager em = getEntityMgr();
		User user = em.find(User.class, userId);
		em.close();
		
		if (user != null) {
			return user.getUserName();
		} else {
			return "null";
		}
	}
	
	/**
	 * JPA test - add user
	 * @param userId
	 * @return String
	 */
	@RequestMapping(value = "/testAddUser", method = RequestMethod.GET)
	@ResponseBody
	public String testAddUser(@RequestParam(value="userId",required = true) long userId) {
		EntityManager em = getEntityMgr();
		User user = new User(userId, "test" + userId);
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
		return "success";
	}

}
