package com.spring.securityapi.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.securityapi.entity.RoleApp;
import com.spring.securityapi.entity.UserApp;
import com.spring.securityapi.repository.RoleAppRepository;
import com.spring.securityapi.repository.UserAppRepository;

@Service
@Transactional
public class serviceApp implements IServiceApp{

	@Autowired
	RoleAppRepository rr;
	
	@Autowired
	UserAppRepository ur;
	
	@Autowired PasswordEncoder passwordencoder;
	
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
	public RoleApp addRoleApp(RoleApp r) {
		// TODO Auto-generated method stub
		return rr.save(r);
	}

	@Override
	public UserApp addUserApp(UserApp u) {
		// TODO Auto-generated method stub
		String pw = u.getPassword();
		u.setPassword(passwordencoder.encode(pw));
		return ur.save(u);
	}

	@Override
	public void deleteUser(int id) throws IOException {
		// TODO Auto-generated method stub
		ur.deleteById(id);
	}

	@Override
	public UserApp LoadUserByUsername(String username) {
		// TODO Auto-generated method stub
		return ur.findByUsername(username);
	}

}
