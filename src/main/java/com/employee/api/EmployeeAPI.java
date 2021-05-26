package com.employee.api;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.EmployeeDTO;
import com.employee.exception.EmployeeException;
import com.employee.service.EmployeeService;

@RestController
@RequestMapping(value ="/employee")
public class EmployeeAPI {
    
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private Environment environment;
	

	@GetMapping(value="/employees")
	public ResponseEntity<List<EmployeeDTO>>getAllEmployees()throws EmployeeException{
			List<EmployeeDTO> employeeDTOs = employeeService.getAllEmployees();
			return new ResponseEntity<>(employeeDTOs,HttpStatus.OK);
	}
	@GetMapping(value="/employees/{employeeId}")
	public ResponseEntity<EmployeeDTO>getEmployee(@PathVariable Integer employeeId)throws
	         EmployeeException{
			EmployeeDTO employeeDTO = employeeService.getEmployee(employeeId);
			return new ResponseEntity<>(employeeDTO,HttpStatus.OK);
	}
	@PostMapping(value="/employees")
	public ResponseEntity<String> addEmployee(@RequestBody EmployeeDTO employeeDTO)throws
	EmployeeException{
		Integer employeeId = employeeService.addEmployee(employeeDTO);
		String successMessage = environment.getProperty("API.INSERT_SUCCESS") + employeeId;
		return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
	}
	
	@PutMapping(value="/employees/{employeeId}")
	public ResponseEntity<String> updateEmployee(@PathVariable Integer employeeId,
			@RequestBody EmployeeDTO employee )throws EmployeeException{
		 employeeService.updateEmployee(employeeId, employee.getEmailId());
		 String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
				return new  ResponseEntity<>(successMessage , HttpStatus.OK);
		
	}
	@DeleteMapping(value="/employees/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer employeeId)throws
	EmployeeException{
		employeeService.deleteEmployee(employeeId);
		String successMessage = environment.getProperty("API.DELETE_SUCCESS");
		return  new ResponseEntity<>(successMessage , HttpStatus.OK);
}
	
}
