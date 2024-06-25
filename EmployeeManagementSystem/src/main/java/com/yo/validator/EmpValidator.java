package com.yo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.yo.model.Employee;

@Component
public class EmpValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Employee.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Employee emp = (Employee) target;
		if (emp.getName().isEmpty()) {
			errors.rejectValue("name", "emp.name.required");
		} else if (emp.getName().length() < 3) {
			errors.rejectValue("name", "emp.name.minlength");
		}
		if (emp.getLocation().isEmpty()) {
			errors.rejectValue("location", "emp.location.required");
		}
		if (emp.getSal()==null) {
			errors.rejectValue("sal", "emp.sal.required");
		}
		else if ( emp.getSal().isNaN()) {
			errors.rejectValue("sal", "emp.sal.numeric");	
		}
		else if (emp.getSal()<400 || emp.getSal()>90000) {
			errors.rejectValue("sal", "emp.sal.range");
		}
		if (emp.getDeptNo()==null) {
			errors.rejectValue("deptNo", "emp.deptNo.required");
		}
		else if ( ((Float)emp.getDeptNo().floatValue()).isNaN()) {
			errors.rejectValue("deptNo", "emp.deptNo.numeric");	
		}
		else if (! (emp.getDeptNo()==10 || emp.getDeptNo()==20 || emp.getDeptNo()==30)) {
			errors.rejectValue("deptNo", "emp.deptNo.type");
		}
	}

}
