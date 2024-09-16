package com.luv2code.spring_security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.luv2code.spring_security.model.Employee;


public interface EmployeeRepo extends JpaRepository<Employee, Integer>{
    
}