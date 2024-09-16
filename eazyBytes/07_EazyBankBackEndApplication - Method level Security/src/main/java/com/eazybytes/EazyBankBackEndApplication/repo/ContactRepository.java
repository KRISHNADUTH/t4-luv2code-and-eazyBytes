package com.eazybytes.EazyBankBackEndApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.EazyBankBackEndApplication.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
	
	
}