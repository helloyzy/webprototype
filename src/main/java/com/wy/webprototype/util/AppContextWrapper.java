package com.wy.webprototype.util;

import static com.wy.webprototype.util.ComLogger.error;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Convenient class for accessing the application context 
 * @author whitman.yang
 *
 */
public class AppContextWrapper implements ApplicationContextAware {
	
	private ApplicationContext context;
	
	private static AppContextWrapper instance;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		if (instance == null) {
			instance = new AppContextWrapper();
			instance.context = applicationContext;
		}
	}
	
	public static Object get(String name) {
		try {
			return instance.context.getBean(name);
		} catch (BeansException e) {
			error(e);
			return null;
		}
	}
	
	public static <T> T get(Class<T> clazz) {
		try {
			return instance.context.getBean(clazz);
		} catch (BeansException e) {
			error(e);
			return null;
		}
	}
	
	public static EntityManager getEntityMgr() {
		EntityManagerFactory factory = get(EntityManagerFactory.class);
		if (factory != null) {
			return factory.createEntityManager();
		}
		return null;
	}

}
