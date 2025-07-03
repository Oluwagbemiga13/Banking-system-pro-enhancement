package com.jindero.banking.controller;


import com.jindero.banking.entity.Account;
import com.jindero.banking.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class BankController {

  @Autowired
  private BankService bankService;

  // GET /api/accounts - všechny účty
  @GetMapping
  public List<Account> getAllAccounts(){
    return bankService.getAllAccounts();
  }

  // GET /api/accounts/{id} - jeden účet
  @GetMapping("/{id}")
  public Account getAccountById(@PathVariable Long id){
    Optional<Account> account = bankService.getAccountById(id);

    if (account.isPresent()){
      return account.get();
    }else {
      return null;
    }
  }

  // POST /api/accounts.... - vytvořit účet
  @PostMapping
  public Account createAccount(@RequestParam String type,
                               @RequestParam String number,
                               @RequestParam String owner,
                               @RequestParam double balance){
    return bankService.createAccount(type,number,owner,balance);
  }

  // POST /api/accounts/1/deposit?amount=500
  @PostMapping("/{id}/deposit")
  public Account deposit(@PathVariable Long id, @RequestParam double amount){
    return bankService.deposit(id, amount);
  }

  // POST /api/accounts/1/deposit?amount=200
  @PostMapping("/{id}/withdraw")
  public Account withdraw(@PathVariable Long id, @RequestParam double amount){
    return bankService.withdraw(id, amount);
  }

}
