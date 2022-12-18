package com.spring.securityapi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity @AllArgsConstructor @NoArgsConstructor
public class RoleApp {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	
	@OneToMany(mappedBy = "roleApp", cascade = CascadeType.ALL)
    List<UserApp> userApp = new ArrayList<>();
	
}
