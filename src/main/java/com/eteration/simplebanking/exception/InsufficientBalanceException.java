package com.eteration.simplebanking.exception;

/**
 * Exception thrown when there is insufficient balance for a transaction.
 */
public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
