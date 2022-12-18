package com.spring.securityapi.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.securityapi.entity.RoleApp;
import com.spring.securityapi.entity.UserApp;

@Service
public interface IServiceApp {

	public List<RoleApp> getListeRole();
	public List<UserApp> getListeUser();
	public void addRoleApp(RoleApp r);
	public void addUserApp(UserApp u);
	public void deleteUser(int id) throws IOException; 
}
