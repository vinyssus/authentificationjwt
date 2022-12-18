package com.spring.securityapi.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity @NoArgsConstructor @AllArgsConstructor
public class UserApp {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	
	@ManyToOne
	private RoleApp roleApp;
}
