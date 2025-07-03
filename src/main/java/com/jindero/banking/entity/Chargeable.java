package com.jindero.banking.entity;

public interface Chargeable {

  double calculateFees();
  void applyMonthlyFee();
}
