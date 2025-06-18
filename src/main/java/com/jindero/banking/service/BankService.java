package com.jindero.banking.service;


import com.jindero.banking.entity.Account;
import com.jindero.banking.entity.BusinessAccount;
import com.jindero.banking.entity.CheckingAccount;
import com.jindero.banking.entity.SavingsAccount;
import com.jindero.banking.exception.AccountNotFoundException;
import com.jindero.banking.exception.InsufficientFundsException;
import com.jindero.banking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {

  @Autowired
  private AccountRepository accountRepository;

  //vytvořit account

  public Account createAccount(String accountType, String accountNumber,
                               String ownerName, double initialBalance){

    Account account =  switch (accountType.toUpperCase()){
      case "SAVINGS" -> new SavingsAccount(accountNumber, ownerName, initialBalance);
      case "CHECKING" -> new CheckingAccount(accountNumber, ownerName, initialBalance);
      case "BUSINESS" -> new BusinessAccount(accountNumber, ownerName, initialBalance);
      default -> throw new IllegalArgumentException("Invalid account type: " + accountType);
    };
    return accountRepository.save(account);
  }

  // Zobrazit všechny učty
  public List<Account> getAllAccounts(){
    return accountRepository.findAll();
  }

  // Najít účet pomocí ID
  public Optional<Account> getAccountById(Long id){
    if (id == null || id <= 0){
      return Optional.empty();
    }
    return accountRepository.findById(id);
  }

  // Vložení peněz
  public Account deposit(Long accountId, double amount){
    if (amount <= 0){
      throw new IllegalArgumentException("Deposit amount must be positive");
    }

    Optional<Account> accountOpt = accountRepository.findById(accountId);
    if (accountOpt.isPresent()){
      Account account = accountOpt.get();
      account.deposit(amount);
      return accountRepository.save(account);
    }
    throw new AccountNotFoundException("Account with ID " + accountId + " not found");
  }

  // Výběr peněz
  public Account withdraw(Long accountId,double amount){
    if (amount <= 0){
      throw new IllegalArgumentException("Withdrawal amount must be positive");
    }

    Optional<Account> accountOpt = accountRepository.findById(accountId);
    if (accountOpt.isPresent()){
      Account account = accountOpt.get();
      if (account.withdraw(amount)){
        return accountRepository.save(account);
      }
      throw  new InsufficientFundsException("Insufficient funds for withdrawal of " + amount);
    }
    throw new AccountNotFoundException("Account with ID "+ accountId+ " not found");
  }

}
