package com.luv2code.spring_security.service;

import java.util.List;

import com.luv2code.spring_security.model.Employee;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);

}
