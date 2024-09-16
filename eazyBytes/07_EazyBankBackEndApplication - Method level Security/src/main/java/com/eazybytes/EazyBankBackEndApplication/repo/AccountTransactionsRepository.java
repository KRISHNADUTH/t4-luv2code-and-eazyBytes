package com.eazybytes.EazyBankBackEndApplication.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.EazyBankBackEndApplication.model.AccountTransactions;

@Repository
public interface AccountTransactionsRepository extends JpaRepository<AccountTransactions, String> {
	
	List<AccountTransactions> findByCustomerIdOrderByTransactionDtDesc(int customerId);

}