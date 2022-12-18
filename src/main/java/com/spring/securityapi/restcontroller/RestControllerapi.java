package com.spring.securityapi.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.securityapi.entity.UserApp;
import com.spring.securityapi.services.serviceApp;

@RestController
public class RestControllerapi {

	@Autowired
	serviceApp sc;
	
	@GetMapping("/users")
	List<UserApp> getAllUser(){
		return sc.getListeUser();
		
	}
}
