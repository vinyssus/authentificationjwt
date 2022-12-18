package com.spring.securityapi.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.securityapi.entity.UserApp;

@Repository
public interface UserAppRepository extends JpaRepository<UserApp, Integer>{

}
