package com.jindero.banking.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessAccountTest {

  @Test
  void testCalculateInterest(){

    // účet
    BusinessAccount account = new BusinessAccount("BUS001", "Test company", 1000.0);

    // metoda na úrok
    double interest = account.calculateInterest();

    // kontrola
    assertEquals(10.0, interest,0.01,"Úrok z 1000Kč by měl být 10Kč");
  }

  @Test
  void testCalculateFees(){

    // účet
    BusinessAccount account = new BusinessAccount("BUS002", "Test company",1000.0);

    // metoda na poplatek
    double fees = account.calculateFees();

    // kontrola
    assertEquals(200, fees, 0.01, "Poplatek by měl být 200Kč");
  }

  @Test
  void testApplyMonthlyFees(){

    // účet
    BusinessAccount account = new BusinessAccount("BUS003", "Test company", 1000.0);

    // metoda na aplikování poplatků
    account.applyMonthlyFee();

    assertEquals(800, account.getBalance(), 0.01, "Po započtení poplatku 50Kč by měl být zůstatek 800Kč");
  }

  @Test
  void testGetAccountType(){

    // účet
    BusinessAccount account = new BusinessAccount("BUS004", "Test company", 1000.0);

    // metoda
    String accountType = account.getAccountType();

    assertEquals("Business Account",accountType);
  }

}
