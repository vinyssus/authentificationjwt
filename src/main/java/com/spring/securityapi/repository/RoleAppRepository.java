package com.spring.securityapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.securityapi.entity.RoleApp;

@Repository
public interface RoleAppRepository extends JpaRepository<RoleApp, Integer>{
 
//	@Query(value = "select r from RoleApp r where r.nom like %:nom%")
	RoleApp findByNom(String nom);
}
