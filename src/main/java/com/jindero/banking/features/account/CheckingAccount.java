package com.jindero.banking.features.account;


import com.jindero.banking.features.user.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("CHECKINGS")
public class CheckingAccount extends Account implements Chargeable {

    //Konstruktor

    public CheckingAccount() {
        super();
    }

    public CheckingAccount(String accountNumber, double balance, User user) {
        super(accountNumber, balance, user, AccountType.CHECKING);
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
