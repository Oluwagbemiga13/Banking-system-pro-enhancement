package com.jindero.banking.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SAVINGS")
public class SavingsAccount extends Account {

  //Konstruktor

  public SavingsAccount(){
    super();
  }

  public SavingsAccount(String accountNumber, String ownerName, double balance) {
    super(accountNumber, ownerName, balance);
  }

  @Override
  public double calculateInterest() {
    return balance * 0.02;
  }

  @Override
  public String getAccountType() {
    return "Savings Account";
  }
}
