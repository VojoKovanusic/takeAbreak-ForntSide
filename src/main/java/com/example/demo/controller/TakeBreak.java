package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class TakeBreak { 
	
	private EmployeeService service;

	@Autowired
	public TakeBreak(EmployeeService service) {
 
		this.service = service;
	}


	@PutMapping(value = "/empoloyee/take/break")
	public Employee takeABreak(@RequestBody Employee employee )throws InterruptedException  {
		return service.takeABreak(employee);
	}
	 
	
	@GetMapping(value = "/empoloyees/on/break")
	public List<Employee> getEmployeesOnBreak() {
		 
		return service.getEmployeesOnBreak();
	}


	
}
