package com.jindero.banking.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavingsAccountTest {

  @Test
  void testCalculateInterest(){

    // účet
    SavingsAccount account = new SavingsAccount("SAV001", "Test user", 1000.0);

    // metoda na úrok
    double interest = account.calculateInterest();

    // kontrola
    assertEquals(20.0, interest,0.01,"Úrok z 1000Kč by měl být 20Kč");
  }


  @Test
  void testGetAccountType(){

    // účet
    SavingsAccount account = new SavingsAccount("SAV004", "Test user", 1000.0);

    // metoda
    String accountType = account.getAccountType();

    assertEquals("Savings Account",accountType);
  }

}