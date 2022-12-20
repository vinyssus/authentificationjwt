package com.spring.securityapi.restcontroller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
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
	@PostAuthorize("hasAuthority('MANAGER')")
	List<UserApp> getAllUser(){
		return sc.getListeUser();
	}
	
	@PostMapping("/saveUser")
	@PostAuthorize("hasAuthority('ADMIN')")
	public UserApp saveUser(@RequestBody UserApp u) {
		return sc.addUserApp(u);	
	}
	
	
	@PostMapping("/saveRole")
	public RoleApp saveRole(@RequestBody RoleApp r) {
		return sc.addRoleApp(r);
	}
	
	@GetMapping(path="/refreshToken")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
		String authToken = request.getHeader("Authorization");
		if(authToken!=null && authToken.startsWith("Bearer ")) {
try {
				
				String jwt = authToken.substring(7);
				Algorithm algorithm = Algorithm.HMAC256("monsecretVinyssus");
				JWTVerifier jwtVerifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT =  jwtVerifier.verify(jwt);
				String username = decodedJWT.getSubject();
				UserApp userApp = serviceApp.loa
			} catch (Exception e) {
				
				response.setHeader("error-message", e.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
			}
		}
	}
}
