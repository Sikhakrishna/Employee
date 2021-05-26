package com.employee.repository;

import org.springframework.data.repository.CrudRepository;

import com.employee.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee , Integer> {
	
public Employee findByName(String name);


	}
