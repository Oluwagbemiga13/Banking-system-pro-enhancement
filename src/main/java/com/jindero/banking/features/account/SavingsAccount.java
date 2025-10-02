package com.jindero.banking.features.account;


import com.jindero.banking.features.user.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SAVINGS")
public class SavingsAccount extends Account {

    //Konstruktor

    public SavingsAccount() {
        super();
    }

    public SavingsAccount(String accountNumber, double balance, User user) {
        super(accountNumber, balance, user, AccountType.SAVINGS);
    }

}
