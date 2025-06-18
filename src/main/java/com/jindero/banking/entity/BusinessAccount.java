package com.jindero.banking.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BUSINESS")
public class BusinessAccount extends Account implements dev.jov.Chargeable {

  //Konstruktor

  public BusinessAccount(){
    super();
  }

  public BusinessAccount(String accountNumber, String ownerName, double balance) {
    super(accountNumber, ownerName, balance);
  }

  @Override
  public double calculateInterest() {
    return balance * 0.01;
  }

  @Override
  public String getAccountType() {
    return "Business Account";
  }


  @Override
  public double calculateFees() {
    double fees = 200.00;
    return fees;
  }

  @Override
  public void applyMonthlyFee() {
    balance -= calculateFees();
    System.out.println("Odečteno " + calculateFees() + " z účtu " + accountNumber);
  }
}
