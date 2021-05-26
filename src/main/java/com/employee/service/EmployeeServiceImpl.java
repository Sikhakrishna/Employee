package com.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.dto.EmployeeDTO;
import com.employee.entity.Employee;
import com.employee.exception.EmployeeException;
import com.employee.repository.EmployeeRepository;
@Service(value="employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService { 
   
	@Autowired
	private EmployeeRepository employeeRepository;
	
	 @Override
	    public Employee getEmployeeByName(String name) {
	        return employeeRepository.findByName(name);
	    }
	
	@Override
	public  EmployeeDTO getEmployee(Integer employeeId)throws  EmployeeException{
		Optional<Employee> optional  = employeeRepository.findById(employeeId);
		Employee employee = optional.orElseThrow(()-> new 
				EmployeeException("Service.EMPLOYEE_NOT_FOUND"));
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmailId(employee.getEmailId());
        employeeDTO.setDesignation(employee.getDesignation());
        return employeeDTO;	
	}
	 @Override
	 public Integer addEmployee(EmployeeDTO employeeDTO) throws EmployeeException{
		 Employee employeeEntity = new Employee();
		 employeeEntity.setEmployeeId(employeeDTO.getEmployeeId());
		 employeeEntity.setName(employeeDTO.getName());
		 employeeEntity.setEmailId(employeeDTO.getEmailId());
		 employeeEntity.setDesignation(employeeDTO.getDesignation());
		 Employee employeeEntity2 = employeeRepository.save(employeeEntity); 
		 return employeeEntity2.getEmployeeId();
		
	 }
	 @Override
	 public void updateEmployee(Integer employeeId,String emailId)throws EmployeeException{
		 Optional<Employee>employee = employeeRepository.findById(employeeId);
		 Employee e = employee.orElseThrow(()-> new 
					EmployeeException("Service.EMPLOYEE_NOT_FOUND"));
		 e.setEmailId(emailId);
	 }
	 
	 @Override
	 public void deleteEmployee(Integer employeeId)throws EmployeeException{
		 Optional<Employee>employee = employeeRepository.findById(employeeId);
		 employee.orElseThrow(()-> new EmployeeException("Service.EMPLOYEE_NOT_FOUND"));
		 employeeRepository.deleteById(employeeId);
		 
	 }
	 
	 @Override
	 public List<EmployeeDTO>getAllEmployees()throws EmployeeException{
		 Iterable<Employee> employees = employeeRepository.findAll();
		 List<EmployeeDTO> employeeDTOs = new ArrayList<>();
		 employees.forEach(employee ->{
			 EmployeeDTO emp = new EmployeeDTO();
			 emp.setEmployeeId(employee.getEmployeeId());
			 emp.setName(employee.getName());
			 emp.setEmailId(employee.getEmailId());
			 emp.setDesignation(employee.getDesignation());
			 employeeDTOs.add(emp);
		 } );
		 if(employeeDTOs.isEmpty())
			 throw new EmployeeException("Service.EMPLOYEES.NOT_FOUND");
			 
		return employeeDTOs;
		 
		 }
	
}
