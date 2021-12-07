package com.switchfully.customer.exception;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String message) {
        super(message);
    }
}
