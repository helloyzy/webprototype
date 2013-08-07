package com.wy.webprototype.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComLogger {
	
	private static final Logger log = LoggerFactory.getLogger(ComLogger.class);
	
	public static void error(Throwable e) {
		log.error(e.getMessage(), e);
    }
    
    public static void error(String errorMsg) {
    	log.error(errorMsg);
    }
    
    public static void error(String desp, Throwable e) {
    	log.error(desp, e);
    }
    
    public static boolean isInfo() {
    	return log.isInfoEnabled();
    }
    
    public static void info(String message) {
    	log.info(message);
    }

}
