package com.jindero.banking.shared.exception;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(String message) {
        super(message);
    }

    // Defaultní message, takže když někdo zavolá bez parametru, tak se použije tato
    public InsufficientFundsException(){
        super("Nedostatečný zůstatek na účtu.");
    }}



