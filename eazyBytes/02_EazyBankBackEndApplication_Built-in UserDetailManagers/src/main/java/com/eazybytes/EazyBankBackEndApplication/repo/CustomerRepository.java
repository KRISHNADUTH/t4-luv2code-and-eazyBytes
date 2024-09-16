package com.eazybytes.EazyBankBackEndApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eazybytes.EazyBankBackEndApplication.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    
}
