package com.bank.ebankingbackend.services;

import com.bank.ebankingbackend.entities.BankAccount;
import com.bank.ebankingbackend.entities.CurrentAccount;
import com.bank.ebankingbackend.entities.SavingAccount;
import com.bank.ebankingbackend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter(){
        BankAccount bankAccount=bankAccountRepository.findById("1a631d7a-657c-4932-aead-86334d4cc4fa").orElse(null);

        if(bankAccount!=null) {


            System.out.println("********************************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());
            if (bankAccount instanceof CurrentAccount) {
                System.out.println("Over draft=>"+((CurrentAccount) bankAccount).getOverDraft());

            } else if (bankAccount instanceof SavingAccount) {
                System.out.println("Rate=>"+((SavingAccount) bankAccount).getInterestRate());

            }
            bankAccount.getAccountOperations().forEach(op -> {

                System.out.println("==============================");
                System.out.println(op.getType() + "\t" + op.getAmount() + "\t" + op.getOperationDate());


            });
        }
    }
}