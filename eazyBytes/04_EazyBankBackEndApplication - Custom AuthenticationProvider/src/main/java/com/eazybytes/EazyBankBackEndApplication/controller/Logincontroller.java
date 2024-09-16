package com.eazybytes.EazyBankBackEndApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.EazyBankBackEndApplication.model.Customer;
import com.eazybytes.EazyBankBackEndApplication.repo.CustomerRepository;

@RestController
public class Logincontroller {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Object registerUser(@RequestBody Customer customer)
    {

        ResponseEntity<String> response = null;
        try {
            customer.setPwd(passwordEncoder.encode(customer.getPwd()));
            System.out.println(customer);
            Customer savedCustomer = customerRepository.save(customer);
            if(savedCustomer.getId()>0)
            {
                response = ResponseEntity.status(HttpStatus.CREATED).body("Created successfully");
            }
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return response;
    }
}