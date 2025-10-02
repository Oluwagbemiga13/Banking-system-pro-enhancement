package com.jindero.banking.features.account;

public interface Chargeable {

    double calculateFees();

    void applyMonthlyFee();
}
