package com.jindero.banking.service;

import com.jindero.banking.entity.Account;
import com.jindero.banking.entity.SavingsAccount;
import com.jindero.banking.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BankServiceTest {

  @Mock
  private AccountRepository accountRepository; // falešná databáze

  @InjectMocks
  private BankService bankService; // Skutečná service s falešnou databází

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  //

  @Test
  void testCreateAccount_validSavingsAccount(){

    // účet
    SavingsAccount account = new SavingsAccount("SAV001", "Test user",1000.0);

    // mock vrátí tento účet po zavolání save()
    when(accountRepository.save(any(SavingsAccount.class))).thenReturn(account);

    Account result = bankService.createAccount("SAVINGS","SAV001",
            "Test user",1000.0);

    assertNotNull(result);
    assertEquals("SAV001", result.getAccountNumber());
    assertEquals("Test user", result.getOwnerName());
    assertEquals(1000.0, result.getBalance(), 0.01);
    assertTrue(result instanceof SavingsAccount);

    verify(accountRepository, times(1)).save(any(SavingsAccount.class));
  }

  @Test
  void testCreateAccount_invalidSavingsAccount(){

    // účet
    SavingsAccount account = new SavingsAccount("SAV002", "Test user",1000.0);

    // mock vrátí tento účet po zavolání save()
    when(accountRepository.save(any(SavingsAccount.class))).thenReturn(account);

    Account result = bankService.createAccount("SAVINGS","SAV001",
            "Test user",1000.0);

    assertNotNull(result);
    assertEquals("SAV001", result.getAccountNumber());
    assertEquals("Test user", result.getOwnerName());
    assertEquals(1000.0, result.getBalance(), 0.01);
    assertTrue(result instanceof SavingsAccount);

    verify(accountRepository, times(1)).save(any(SavingsAccount.class));
  }

}