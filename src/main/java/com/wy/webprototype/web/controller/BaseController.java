package com.wy.webprototype.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.wy.webprototype.util.ComLogger;

public class BaseController {
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleGenericExceptions(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		ComLogger.error(ex);
	}

}
