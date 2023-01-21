package com.spring.securityapi;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import com.spring.securityapi.entity.RoleApp;
import com.spring.securityapi.entity.UserApp;
import com.spring.securityapi.services.IServiceApp;



@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityapiApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SecurityapiApplication.class, args);
	}

	@Autowired
	IServiceApp sc;
	
	
	@Override
	public void run(String...args )throws Exception{
		
sc.addRoleApp(new RoleApp(1,"ADMIN"));
sc.addRoleApp(new RoleApp(3,"MANAGER"));
sc.addRoleApp(new RoleApp(2,"USER"));
sc.addRoleApp(new RoleApp(4,"GESTIONNAIRE"));
		
		
		sc.addUserApp( new UserApp(1,"vinyssus","1234",new ArrayList<>()));
		sc.addUserApp( new UserApp(2,"titus","1234",new ArrayList<>()));
		sc.addUserApp( new UserApp(3,"valarius","1234",new ArrayList<>()));
		sc.addUserApp(new UserApp(4,"viny","1234",new ArrayList<>()));
		
         sc.addRoleToUsers("vinyssus", "ADMIN");
         sc.addRoleToUsers("titus", "MANAGER");
         sc.addRoleToUsers("valarius", "USER");
         sc.addRoleToUsers("viny", "USER");
         sc.addRoleToUsers("viny", "GESTIONNAIRE");
         sc.addRoleToUsers("viny", "MANAGER");
	}
	
	}
