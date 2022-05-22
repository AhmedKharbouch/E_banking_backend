package com.bank.ebankingbackend.web;

import com.bank.ebankingbackend.dtos.AccountHistoryDTO;
import com.bank.ebankingbackend.dtos.AccountOperationDTO;
import com.bank.ebankingbackend.dtos.BankAccountDTO;
import com.bank.ebankingbackend.dtos.CustomerDTO;
import com.bank.ebankingbackend.exceptions.BalanceNotSufficentException;
import com.bank.ebankingbackend.exceptions.BankAccountNotFoundException;
import com.bank.ebankingbackend.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankAccountRestAPI {
 private BankAccountService bankAccountService;

@GetMapping("/accounts/{accountId}")
public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
 return bankAccountService.getBankAccount(accountId);
}
@GetMapping("/accounts")
public List<BankAccountDTO> listAccounts(){

 return  bankAccountService.bankAccountList();
}

 @GetMapping("/accounts/{accountId}/operations")
 public List<AccountOperationDTO> getHistory(@PathVariable String accountId){
  return bankAccountService.accountHistory(accountId);
 }

 @GetMapping("/accounts/{accountId}/pageOperations")
 public AccountHistoryDTO getAccountHistory(@PathVariable String accountId,
                                            @RequestParam(name = "page",defaultValue = "0") int page ,
                                            @RequestParam(name = "size",defaultValue = "5") int size             ) throws BankAccountNotFoundException {
  return bankAccountService.getAccountHistory(accountId,page,size);
 }

 @PostMapping("/accounts/debit/{accountId}")
 public void saveDebit(@PathVariable String accountId,@RequestBody AccountOperationDTO accountOperationDTO) throws BankAccountNotFoundException, BalanceNotSufficentException {
  bankAccountService.debit(accountId,accountOperationDTO.getAmount(),"DEBIT");
 }

 @PostMapping("/accounts/credit/{accountId}")
 public void saveCredit(@PathVariable String accountId,@RequestBody AccountOperationDTO accountOperationDTO) throws BankAccountNotFoundException{
  bankAccountService.credit(accountId,accountOperationDTO.getAmount(),"CREDIT");
 }

 @PostMapping("/accounts/transfer/{accountIdSource}/{accountIdDestination}")
 public void saveTransfer(@PathVariable String accountIdSource,@PathVariable String accountIdDestination,@RequestBody AccountOperationDTO accountOperationDTO) throws BankAccountNotFoundException, BalanceNotSufficentException {
  bankAccountService.transfer(accountIdSource,accountIdDestination,accountOperationDTO.getAmount());
 }




}
