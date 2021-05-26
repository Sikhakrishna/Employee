package com.employee;


import static org.assertj.core.api.Assertions.assertThat;

//import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

//import com.employee.dto.EmployeeDTO;
import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
public class EmployeeTests {

	@Autowired
	EmployeeRepository repo;
	@Autowired
    private TestEntityManager entityManager;
	
	@Test
	public void testCreateEmployee()throws Exception {
		
		Employee emp = new Employee();
		emp.setEmployeeId(5);
		emp.setName("Alex");
		emp.setEmailId("Alex@infy.com");
		emp.setDesignation("executive");
    	Employee savedemp = repo.save(emp);	
    	
    	Assertions.assertNotNull(savedemp);
    	
	}
	@Test
	public void getEmployeeByNameExists() {
		
		Employee emp = new Employee();
		emp.setName("alex");
	    entityManager.persist(emp);

	    Employee found = repo.findByName(emp.getName());
	    assertThat(found.getName()).isEqualTo(emp.getName());
    
	}  
	
	@Test
	public void getEmployeeByNameNotExists() {
		
		Employee emp = new Employee();
		emp.setName("a@ex");
	    entityManager.persist(emp);
	    entityManager.flush();

	    Employee found = repo.findByName(emp.getName());
	    Assertions.assertNotNull(found);
    
	}  

}
