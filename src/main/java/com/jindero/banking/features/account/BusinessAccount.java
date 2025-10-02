package com.jindero.banking.features.account;


import com.jindero.banking.features.user.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BUSINESS")
public class BusinessAccount extends Account implements Chargeable {

    //Konstruktor

    public BusinessAccount() {
        super();
    }

    public BusinessAccount(String accountNumber, double balance, User user) {
        super(accountNumber, balance, user);
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
