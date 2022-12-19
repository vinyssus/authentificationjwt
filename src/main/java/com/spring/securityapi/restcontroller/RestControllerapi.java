package com.spring.securityapi.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.securityapi.entity.RoleApp;
import com.spring.securityapi.entity.UserApp;
import com.spring.securityapi.services.serviceApp;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/gestionuser")
public class RestControllerapi {

	@Autowired
	serviceApp sc;
	
	@GetMapping("/users")
	List<UserApp> getAllUser(){
		return sc.getListeUser();
	}
	
	@PostMapping("/saveUser")
	public UserApp saveUser(@RequestBody UserApp u) {
		return sc.addUserApp(u);	
	}
	
	
	@PostMapping("/saveRole")
	public RoleApp saveRole(@RequestBody RoleApp r) {
		return sc.addRoleApp(r);
	}
}
