package com.jindero.banking.shared.exception;

import java.util.UUID;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String message) {
        super(message);
    }
    public AccountNotFoundException(UUID id) {
        super("Account with ID " + id + " not found.");
    }

    public AccountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
