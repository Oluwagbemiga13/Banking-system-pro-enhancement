package com.jindero.banking.features.account;


import com.jindero.banking.features.user.User;
import com.jindero.banking.features.user.UserRepository;
import com.jindero.banking.shared.exception.AccountNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    //vytvořit account

    public Account createAccount(Long userId, String accountType,
                                 String accountNumber, double initialBalance) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found"));

        Account account = switch (accountType.toUpperCase()) {
            case "SAVINGS" -> new SavingsAccount(accountNumber, initialBalance, user);
            case "CHECKING" -> new CheckingAccount(accountNumber, initialBalance, user);
            case "BUSINESS" -> new BusinessAccount(accountNumber, initialBalance, user);
            default -> throw new IllegalArgumentException("Invalid account type: " + accountType);
        };
        return accountRepository.save(account);
    }

    // Zobrazit všechny učty
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Najít účet pomocí ID
    // Tohle je zbytečná metoda, protože dělá to samé jako findById z JpaRepository
//    public Optional<Account> getAccountById(Long id) {
//        if (id == null || id <= 0) {
//            return Optional.empty();
//        }
//        return accountRepository.findById(id);
//    }

    // Vložení peněz
    public Account deposit(UUID accountId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        Account account = getById(accountId);
        account.deposit(amount);
        return accountRepository.save(account);
    }

    // Touto metodou získám účet podle jeho UUID a pokud účet neexistuje, vyhodí výjimku AccountNotFoundException
    public Account getById(UUID accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }

    // Výběr peněz
    // Tím že si vyjímky nechám vyhodit jinde, nemusím se tady o ně starat
    public Account withdraw(UUID accountId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        Account account = getById(accountId);
        account.withdraw(amount);
        return accountRepository.save(account);

    }

}
