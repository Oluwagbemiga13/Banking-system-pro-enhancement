package com.jindero.banking.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CHECKINGS")
public class CheckingAccount extends Account implements Chargeable {

  //Konstuktor

  public CheckingAccount(){
    super();
  }

  public CheckingAccount(String accountNumber, String ownerName, double balance) {
    super(accountNumber, ownerName, balance);
  }

  @Override
  public double calculateInterest() {
    return balance * 0.005;
  }

  @Override
  public String getAccountType() {
    return "Checking Account";
  }


  @Override
  public double calculateFees() {
    double fees = 50.00;
    return fees;
  }

  @Override
  public void applyMonthlyFee() {
    double fees = calculateFees();
    balance -= fees;
    System.out.println("Odečteno " + fees + " z účtu " + accountNumber);
  }
}
