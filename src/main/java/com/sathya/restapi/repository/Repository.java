package com.sathya.restapi.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sathya.restapi.models.Employee;


public interface Repository extends JpaRepository<Employee, Long> {

	public Optional<Employee> findByEmail(String email);




}
