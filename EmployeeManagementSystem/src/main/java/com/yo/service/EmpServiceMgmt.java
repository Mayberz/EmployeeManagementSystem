package com.yo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.yo.exception.EmployeeNotFoundException;
import com.yo.model.Employee;
import com.yo.repo.EmpRepo;


@Service
public final class  EmpServiceMgmt implements EmpService{
	
	@Autowired
	private EmpRepo repo;

	
	@Override
	public Iterable<Employee> getAllEmp() {
		return repo.findAll(Sort.by("empNo").ascending());
		
		
	}


	@Override
	public String insertEmp(Employee emp) {
		return "Employee Inserted with  id value "+repo.save(emp).getEmpNo();
	}


	@Override
	public Employee getEmpByEmpNo(int empNo) {
	
		 	Optional<Employee> op=repo.findById(empNo);
		 	if (op.isPresent()) {
				return op.get();
			}
		 	else {
		 		throw new EmployeeNotFoundException("problem in getting records");
		 	}
	}	


	@Override
	public String updateEmp(Employee emp) {
		
		return "Record updated of "+repo.save(emp).getEmpNo()+"empno";
	}


	@Override	
	public String deleteEmpByEmpNo(int empNo) {
		repo.deleteById(empNo);
		return empNo +"Record deleted successfully"; 
	}



}
