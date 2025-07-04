package com.jindero.banking.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckingAccountTest {

  @Test
  void testCalculateInterest(){

    // účet
    CheckingAccount account = new CheckingAccount("CHK001", "Test user", 1000.0);

    // metoda na úrok
    double interest = account.calculateInterest();

    // kontrola
    assertEquals(5.0, interest,0.01,"Úrok z 1000Kč by měl být 5Kč");
  }

  @Test
  void testCalculateFees(){

     // účet
    CheckingAccount account = new CheckingAccount("CHK002", "Test user",1000.0);

    // metoda na poplatek
    double fees = account.calculateFees();

    // kontrola
    assertEquals(50, fees, 0.01, "Poplatek by měl být 50Kč");
  }

  @Test
  void testApplyMonthlyFees(){

    // účet
    CheckingAccount account = new CheckingAccount("CHK003", "Test user", 1000.0);

    // metoda na aplikování poplatků
    account.applyMonthlyFee();

    assertEquals(950, account.getBalance(), 0.01, "Po započtení poplatku 50Kč by měl být zůstatek 950Kč");
  }

  @Test
  void testGetAccountType(){

    // účet
    CheckingAccount account = new CheckingAccount("CHK004", "Test user", 1000.0);

    // metoda
    String accountType = account.getAccountType();

    assertEquals("Checking Account",accountType);
  }

}