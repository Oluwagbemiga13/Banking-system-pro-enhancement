package com.jindero.banking.features.account;


import com.jindero.banking.features.user.User;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  protected String accountNumber;

  protected double balance;

  //Propojeni Account s User
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  //konstruktor

  public Account() {
  }

  public Account( String accountNumber, double balance, User user) {
    this.accountNumber = accountNumber;
    this.balance = balance;
    this.user = user;
  }

  //Abstract metody
  public abstract double calculateInterest();
  public abstract String getAccountType();

  //Metody
  public void deposit(double amount) {
    if (amount <= 0) {
       System.out.println("Chyba! Zadej částku větší než 0!");
       return;
    }
    balance += amount;
    System.out.println("Vloženo: " + amount + " Kč");
  }

  public boolean withdraw(double amount) {
    if (amount > 0 && balance >= amount) {
      balance -= amount;
      System.out.println("Vybráno " + amount + " Kč");
      return true;
    }
    System.out.println("Nedostatek prostředků");
    return false;
  }

  //Getter
  public double getBalance(){
    return balance;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public Long getId() {
    return id;
  }

  public User getUser(){
    return user;
  }

  //Setter

  public void setId(Long id) {
    this.id = id;
  }

  public void setUser(User user){
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Account account = (Account) o;
    return Objects.equals(id, account.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "Account{" +
            "id=" + id +
            ", accountNumber='" + accountNumber + '\'' +
            ", balance=" + balance +
            ", user=" + user +
            '}';
  }
}
