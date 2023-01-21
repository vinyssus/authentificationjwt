package com.spring.securityapi.restcontroller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.securityapi.entity.RoleApp;
import com.spring.securityapi.entity.UserApp;
import com.spring.securityapi.services.serviceApp;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/gestionusers")
public class RestControllerapi {

	@Autowired
	serviceApp sc;
	
	@GetMapping(path="/users")
	@PostAuthorize("hasAuthority('MANAGER')")
	List<UserApp> getAllUser(){
		return sc.getListeUser();
	}
	
	@PostMapping(path="/saveUser")
	@PostAuthorize("hasAuthority('ADMIN')")
	public UserApp saveUser(@RequestBody UserApp u) {
		return sc.addUserApp(u);	
	}
	
	
	@PostMapping(path="/saveRole")
	public RoleApp saveRole(@RequestBody RoleApp r) {
		return sc.addRoleApp(r);
	}
	
	@GetMapping(path="/refreshToken")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("/gestionusers/refreshToken");
		String authToken = request.getHeader("Authorization");
		if(authToken!=null && authToken.startsWith("Bearer ")){
try {
	            
	            System.out.println("condition valide");
				String jwt = authToken.substring(7);
				Algorithm algorithm = Algorithm.HMAC256("monsecretVinyssus");
				JWTVerifier jwtVerifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT =  jwtVerifier.verify(jwt);
				String username = decodedJWT.getSubject();
				UserApp userApp = sc.LoadUserByUsername(username);
				String jwtAccessToken = JWT.create()
						.withSubject(userApp.getUsername())
						.withExpiresAt(new Date(System.currentTimeMillis()+1*60*1000))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles", userApp.getRoleApp().stream().map(r->r.getNom()).collect(Collectors.toList()))
						.sign(algorithm);
				Map<String,String> idToken = new HashMap<>();
				idToken.put("access-token", jwtAccessToken);
				idToken.put("refresh-token", jwt);
				response.setContentType("application/json");
				new ObjectMapper().writeValue(response.getOutputStream(), idToken);
			} catch (Exception e) {
				
				throw e;
			}
		}
		else {
			throw new RuntimeException("Refresh token required!!!");
		}
	}
}
