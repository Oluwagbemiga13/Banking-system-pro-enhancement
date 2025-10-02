package com.jindero.banking.features.account;

import java.math.BigDecimal;

public enum AccountType {
    SAVINGS(BigDecimal.valueOf(0.015), BigDecimal.valueOf(0.0)),
    CHECKING(BigDecimal.valueOf(0.005), BigDecimal.valueOf(50.0)),
    BUSINESS(BigDecimal.valueOf(0.01), BigDecimal.valueOf(200.0));

    private final BigDecimal interestRate;
    private final BigDecimal monthlyFee;

    AccountType(BigDecimal interestRate, BigDecimal monthlyFee) {
        this.interestRate = interestRate;
        this.monthlyFee = monthlyFee;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public static AccountType fromString(String type) {
        return switch (type.trim().toUpperCase()) {
            case "SAVINGS" -> SAVINGS;
            case "CHECKING" -> CHECKING;
            case "BUSINESS" -> BUSINESS;
            default -> throw new IllegalArgumentException("Unknown account type: " + type);
        };
    }
}
