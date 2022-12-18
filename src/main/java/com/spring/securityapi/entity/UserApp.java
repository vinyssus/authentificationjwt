package com.spring.securityapi.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity @NoArgsConstructor @AllArgsConstructor
public class UserApp {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	private String password;

	@ManyToOne
	private RoleApp roleApp;
}
