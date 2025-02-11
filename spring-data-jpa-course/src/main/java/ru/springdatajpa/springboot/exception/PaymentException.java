package ru.springdatajpa.springboot.exception;

public class PaymentException extends RuntimeException{

    public PaymentException(String message) {
        super(message);
    }
}
