package com.jindero.banking.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Account {

  @Column(name = "account_number")
  protected String accountNumber;

  @Column(name = "owner_name")
  protected String ownerName;

  @Column(name = "balance")
  protected double balance;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //konstruktor


  public Account() {
  }

  public Account( String accountNumber, String ownerName, double balance) {
    this.accountNumber = accountNumber;
    this.ownerName = ownerName;
    this.balance = balance;
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

  public String getOwnerName() {
    return ownerName;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public Long getId() {
    return id;
  }

  //Setter

  public void setId(Long id) {
    this.id = id;
  }
}
