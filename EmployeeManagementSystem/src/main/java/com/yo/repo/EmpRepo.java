package com.yo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yo.model.Employee;

public interface EmpRepo extends JpaRepository<Employee, Integer>{
	

}
