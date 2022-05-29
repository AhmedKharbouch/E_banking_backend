package com.bank.ebankingbackend.repositories;

import com.bank.ebankingbackend.dtos.BankAccountDTO;
import com.bank.ebankingbackend.entities.BankAccount;
import com.bank.ebankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {

    List<BankAccount> findBankAccountByCustomerId(Long customerId);
}
