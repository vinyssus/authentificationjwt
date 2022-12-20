package com.spring.securityapi.securityconfig;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring.securityapi.entity.RoleApp;
import com.spring.securityapi.entity.UserApp;
import com.spring.securityapi.services.serviceApp;


@Configuration @EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired serviceApp serviceapp;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				UserApp userApp = serviceapp.LoadUserByUsername(username);
			    Collection<GrantedAuthority> authorities = new ArrayList<>();
			   userApp.getRoleApp().getNom();
			   authorities.add(new SimpleGrantedAuthority(userApp.getRoleApp().getNom()));
				return new User(userApp.getUsername(),userApp.getPassword(),authorities);			}
		});
		
	}
	
   @Override
protected void configure(HttpSecurity http) throws Exception {
	// TODO Auto-generated method stub
	   http.csrf().disable();
	//http.authorizeRequests().anyRequest().permitAll();
	   //http.authorizeHttpRequests().antMatchers("/gestionuser/users/**").permitAll();
	   http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	   //http.formLogin()
	   http.authorizeHttpRequests().antMatchers(HttpMethod.POST,"/gestionuser/save/**").hasAnyAuthority("ADMIN");
	   http.authorizeHttpRequests().antMatchers(HttpMethod.POST,"/gestionuser/users/**").hasAnyAuthority("MANAGER");
	   http.authorizeRequests().anyRequest().authenticated();
	   http.addFilter(new JwtAuthentificationFilter(authenticationManagerBean()));
	   http.addFilterBefore(new JwtAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
}
  
    @Lazy
	@Bean 
	public static PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
		
	}
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
    	return super.authenticationManagerBean();
    }
}
