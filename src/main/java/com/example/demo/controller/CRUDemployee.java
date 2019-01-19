package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CRUDemployee {
	@Autowired
	private EmployeeService service;
	

	@GetMapping(value = "/empoloyees")
	public List<Employee> getAllEmpoloyees() {
		 
		return service.findAll();
	}

	@PostMapping(value = "/empoloyee")
	public void addEmployee(@RequestBody Employee employee) {
		service.addEmployee(employee);

	}

	@PutMapping(value = "/empoloyee")
	public void updateEmployee(@RequestBody Employee employee) {
		service.updateEmployee(employee);

	}

	@DeleteMapping(value = "/empoloyee/{employeeId}")
	public void deleteEmployee(@PathVariable long employeeId) {
		service.deleteEmployee(employeeId);

	}

	@GetMapping(value = "/empoloyee/{employeeId}")
	public Employee getEmployeeById(@PathVariable long employeeId) {
		return service.getEmployeeById(employeeId);
	}
}
