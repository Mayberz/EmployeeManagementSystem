package com.yo.service;


import com.yo.model.Employee;

public interface EmpService {
	public Iterable<Employee> getAllEmp();
	
	public String insertEmp(Employee emp);
	public Employee getEmpByEmpNo(int empNo);
	public String updateEmp(Employee emp);
	public String deleteEmpByEmpNo(int empNo);
	

}
