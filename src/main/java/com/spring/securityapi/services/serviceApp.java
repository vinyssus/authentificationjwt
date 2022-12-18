package com.spring.securityapi.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.securityapi.entity.RoleApp;
import com.spring.securityapi.entity.UserApp;
import com.spring.securityapi.repository.RoleAppRepository;
import com.spring.securityapi.repository.UserAppRepository;

@Service
public class serviceApp implements IServiceApp{

	@Autowired
	RoleAppRepository rr;
	
	@Autowired
	UserAppRepository ur;
	
	@Override
	public List<RoleApp> getListeRole() {
		
		return rr.findAll();
	}

	@Override
	public List<UserApp> getListeUser() {
		// TODO Auto-generated method stub
		return ur.findAll();
	}

	@Override
	public void addRoleApp(RoleApp r) {
		// TODO Auto-generated method stub
		rr.save(r);
	}

	@Override
	public void addUserApp(UserApp u) {
		// TODO Auto-generated method stub
		ur.save(u);
	}

	@Override
	public void deleteUser(int id) throws IOException {
		// TODO Auto-generated method stub
		ur.deleteById(id);
	}

}
