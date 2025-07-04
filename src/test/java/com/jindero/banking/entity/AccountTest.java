package com.jindero.banking.entity;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {


  //DEPOSIT

  @ParameterizedTest
  @CsvSource({
          "SAVINGS, 500.0, 200.0, 700.0",
          "CHECKING, 1000.0, 200.0, 1200.0",
          "BUSINESS, 2000.0, 200.0, 2200.0"
  })
  void testDeposit_validAmount(String accountType, double initialBalance, double depositAmount, double expectedBalance){

    // účet
    Account account = createAccount(accountType, initialBalance);

    // deposit
    account.deposit(depositAmount);

    // kontrola
    assertEquals(expectedBalance, account.getBalance(), 0.01);
  }

  @ParameterizedTest
  @CsvSource({
          "SAVINGS, 500.0, 0.0",
          "CHECKING, 1000.0, -100.0",
          "BUSINESS, 2000.0, -200.0"
  })
  void testDeposit_invalidAmount(String accountType, double initialBalance, double depositAmount){

    // účet
    Account account = createAccount(accountType, initialBalance);

    // deposit
    account.deposit(depositAmount);

    // kontrola
    assertEquals(initialBalance, account.getBalance(), 0.01);
  }


  //WITHDRAW

  @ParameterizedTest
  @CsvSource({
          "SAVINGS, 700.0, 300.0, 400.0",
          "CHECKING, 1000.0, 500.0, 500.0",
          "BUSINESS, 3000.0, 1700.0, 1300.0"
  })
  void testWithdraw_validAmount(String accountType, double initialBalance, double withdrawAmount, double expectedBalance){

    // účet
    Account account = createAccount(accountType, initialBalance);

    // withdraw
    boolean result = account.withdraw(withdrawAmount);

    // kontrola
    assertTrue(result);
    assertEquals(expectedBalance, account.getBalance(), 0.01);
  }

  @ParameterizedTest
  @CsvSource({
          "SAVINGS, 200.0, 0",
          "CHECKING, 700.0, -500.0",
          "BUSINESS, 2500.0, -1700.0"
  })
  void testWithdraw_invalidAmount(String accountType, double initialBalance, double withdrawAmount){

    // účet
    Account account = createAccount(accountType, initialBalance);
    double originalBalance = account.getBalance();

    // withdraw
    boolean result = account.withdraw(withdrawAmount);

    // kontrola
    assertFalse(result);
    assertEquals(originalBalance, account.getBalance(), 0.01);
  }

  @ParameterizedTest
  @CsvSource({
          "SAVINGS, 200.0, 300",
          "CHECKING, 700.0, 1000.0",
          "BUSINESS, 2500.0, 3000.0"
  })
  void testWithdraw_insufficientFunds(String accountType, double initialBalance, double withdrawAmount){

    // účet
    Account account = createAccount(accountType, initialBalance);

    // withdraw
    boolean result = account.withdraw(withdrawAmount);

    // kontrola
    assertFalse(result);
    assertEquals(initialBalance, account.getBalance(), 0.01);
  }


  // metoda pro vytvořeni účtu
  private Account createAccount(String type, double balance) {
    return switch (type) {
      case "SAVINGS" -> new SavingsAccount("ACC001", "Test User", balance);
      case "CHECKING" -> new CheckingAccount("ACC001", "Test User", balance);
      case "BUSINESS" -> new BusinessAccount("ACC001", "Test User", balance);
      default -> throw new IllegalArgumentException("Unknown account type: " + type);
    };
  }

}