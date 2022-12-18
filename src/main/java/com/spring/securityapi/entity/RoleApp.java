package com.spring.securityapi.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity @AllArgsConstructor @NoArgsConstructor
public class RoleApp {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(mappedBy = "roleApp", cascade = CascadeType.ALL)
    List<UserApp> userApp = new ArrayList<>();

	public RoleApp(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}
	
}
