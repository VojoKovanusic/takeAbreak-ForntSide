package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {
	
	 List<Employee>findAll();
	 void addEmployee(Employee employee);
	 void updateEmployee(Employee employee);
	 void deleteEmployee(long employeeId);
	 Employee getEmployeeById(long employeeId);
	 Employee takeABreak(Employee employee) throws InterruptedException ;
	List<Employee> getEmployeesOnBreak();
}
