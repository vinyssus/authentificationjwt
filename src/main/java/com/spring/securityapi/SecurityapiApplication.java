package com.spring.securityapi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.securityapi.entity.RoleApp;
import com.spring.securityapi.entity.UserApp;
import com.spring.securityapi.services.IServiceApp;



@SpringBootApplication
public class SecurityapiApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SecurityapiApplication.class, args);
	}

	@Autowired
	IServiceApp sc;
	
	
	@Bean	PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	@Override
	public void run(String...args )throws Exception{
		RoleApp role1 = new RoleApp(1,"ADMIN");
		RoleApp role2 = new RoleApp(2,"USER");
		RoleApp role3 = new RoleApp(3,"MANAGER");
		UserApp user1 = new UserApp(1,"vinyssus","1234",role1);
		UserApp user2 = new UserApp(2,"titus","1234",role2);
		UserApp user3 = new UserApp(3,"valarius","1234",role3);
		
		sc.addRoleApp(role1);
		sc.addRoleApp(role2);
		sc.addRoleApp(role3);
		
		sc.addUserApp(user1);
		sc.addUserApp(user2);
		sc.addUserApp(user3);
	}
	
	}
