package com.jindero.banking.features.account;


import com.jindero.banking.features.account.dto.CreateAccountRequest;
import com.jindero.banking.shared.exception.AccountNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {


    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // GET /api/accounts - všechny účty
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    // GET /api/accounts/{id} - jeden účet
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable UUID id) {
        Account account;
        try {
            account = accountService.getById(id);
        } catch (AccountNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }


    // POST /api/accounts.... - vytvořit účet
    @PostMapping
    public ResponseEntity<Account> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        Account account = accountService.createAccount(
                request.getUserId(),
                request.getAccountType(),
                request.getAccountNumber(),
                request.getInitialBalance()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(account);

    }

    // POST /api/accounts/1/deposit?amount=500
    @PostMapping("/{id}/deposit")
    public Account deposit(@PathVariable UUID id, @RequestParam double amount) {
        return accountService.deposit(id, amount);
    }

    // POST /api/accounts/1/deposit?amount=200
    @PostMapping("/{id}/withdraw")
    public Account withdraw(@PathVariable UUID id, @RequestParam double amount) {
        return accountService.withdraw(id, amount);
    }

}
