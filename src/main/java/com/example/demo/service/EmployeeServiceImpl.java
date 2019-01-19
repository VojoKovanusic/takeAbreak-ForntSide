package com.example.demo.service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao dao;

	@Autowired
	public EmployeeServiceImpl(EmployeeDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Employee> findAll() {
		return dao.findAll();
	}

	@Override
	public void addEmployee(Employee employee) {
		dao.save(employee);

	}

	@Override
	public void updateEmployee(Employee employee) {
		dao.delete(employee.getEmployeeId());
		dao.save(employee);

	}

	@Override
	public void deleteEmployee(long employeeId) {
		dao.delete(employeeId);

	}

	@Override
	public Employee getEmployeeById(long employeeId) {
		return dao.findOne(employeeId);
	}

	@Override
	public Employee takeABreak(Employee e) throws InterruptedException   {
		Employee employee = dao.findOne(e.getEmployeeId());
		if (isPossibleTakeBreak()) {
			
			employee.setTakeAbreakToday(true);
			
			dao.save(employee);
			
		 
			TimeUnit.SECONDS.sleep(11);
			
			employee.setTakeAbreakToday(false);
			
			dao.save(employee);
			
			 	}

		return employee;
	}

	private boolean isPossibleTakeBreak() {
		short noubmerEmployeeOnBreak = 0;
		
		for (Employee employee : findAll()) {
			if (employee.isTakeAbreakToday() == true)
				noubmerEmployeeOnBreak++;
			
			if (noubmerEmployeeOnBreak >= 2)
			return false;
		}
		
		return true;
	}

	@Override
	public List<Employee> getEmployeesOnBreak() {
	
		return	findAll().stream().filter
			(e-> e.isTakeAbreakToday())
			.collect(Collectors.toList()); 
	 
	}

}
