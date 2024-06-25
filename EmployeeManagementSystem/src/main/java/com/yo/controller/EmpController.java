package com.yo.controller;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yo.model.Employee;
import com.yo.service.EmpService;
import com.yo.validator.EmpValidator;


@Controller
public class EmpController {
	@Autowired
	private EmpService service;
	
	@Autowired
	private EmpValidator validator;
	
	@GetMapping
	public String homePage() {
		return "homePage";
	}
	
	@GetMapping("/emp_report")
	public String showEmpReportFrom(Map<String, Object> map) {
		Iterable<Employee> empList=service.getAllEmp();
		map.put("empsList", empList);
		return "emp_report";
		
	}
	
	@GetMapping("/addEmp")
	public String addEmpform(@ModelAttribute Employee emp) {
		
		return "addEmp";	
	}
	
	@PostMapping("/addEmp")
	public String addEmp(RedirectAttributes attrs,@ModelAttribute Employee emp,BindingResult errors	) {
		if (validator.supports(emp.getClass())) {
			validator.validate(emp, errors);
			
			if (errors.hasErrors()) {
				return "addEmp";
			}
		}
		if (emp.getName().equalsIgnoreCase("Hideo Kojima" )) {
			errors.rejectValue("name","e.name.notAllowed" );
			return "addEmp";
		}
		
		String add=service.insertEmp(emp);
		attrs.addFlashAttribute("msg", add);
		
		return "redirect:emp_report";
	}
	@GetMapping("/editEmp")
	public String editEmpFromPage(@RequestParam int empNo,@ModelAttribute Employee emp) {
		Employee e=service.getEmpByEmpNo(empNo);
		BeanUtils.copyProperties(e, emp);
		return "EditEmp";
		
	}
	@PostMapping("/editEmp")
	public String updateEmp(RedirectAttributes attrs,@ModelAttribute Employee emp,BindingResult errors) {
		if (validator.supports(emp.getClass())) {
			validator.validate(emp, errors);
			
			if (errors.hasErrors()) {
				return "addEmp";
			}
		}
		String update=service.updateEmp(emp);
		attrs.addFlashAttribute("msg", update);
		return "redirect:emp_report";
	}
	@GetMapping("/deleteEmp")
	public String delteEmp(@RequestParam int empNo,RedirectAttributes attrs) {
		String delete=service.deleteEmpByEmpNo(empNo);
		attrs.addFlashAttribute("msg", delete);
		return "redirect:emp_report";
		
	}
	

	
	
	

}
