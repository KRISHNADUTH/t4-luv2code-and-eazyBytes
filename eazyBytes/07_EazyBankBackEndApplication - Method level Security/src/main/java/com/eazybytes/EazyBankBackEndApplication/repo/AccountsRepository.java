package com.eazybytes.EazyBankBackEndApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.EazyBankBackEndApplication.model.Accounts;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
	
	Accounts findByCustomerId(int customerId);

}