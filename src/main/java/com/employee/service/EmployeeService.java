package com.employee.service;

import java.util.List;

import com.employee.dto.EmployeeDTO;
import com.employee.entity.Employee;
import com.employee.exception.EmployeeException;

    public interface EmployeeService {
    public List<EmployeeDTO> getAllEmployees()throws EmployeeException;
    public EmployeeDTO getEmployee(Integer employeeId)throws EmployeeException;
    public Integer addEmployee(EmployeeDTO employeeDTO) throws EmployeeException;
    public void updateEmployee(Integer employeeId,String emailId)throws EmployeeException;
    public void deleteEmployee(Integer employeeId)throws EmployeeException;
    public Employee getEmployeeByName(String name);
    
}
