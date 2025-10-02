package com.jindero.banking.features.account;


import com.jindero.banking.features.user.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("BUSINESS")
public class BusinessAccount extends Account implements Chargeable {

    //Konstruktor

    public BusinessAccount() {
        super();
    }

    public BusinessAccount(String accountNumber, double balance, User user) {
        super(accountNumber, balance, user, AccountType.BUSINESS);
    }


    @Override
    public BigDecimal calculateFees() {
        return getAccountType().getMonthlyFee();
    }

    @Override
    public void applyMonthlyFee() {
        balance = balance.subtract(calculateFees());
        System.out.println("Odečteno " + calculateFees() + " z účtu " + accountNumber);
    }
}
