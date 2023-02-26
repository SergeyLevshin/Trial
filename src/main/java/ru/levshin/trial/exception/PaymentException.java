package ru.levshin.trial.exception;

public class PaymentException extends Throwable {

    private String message;

    public PaymentException(String message) {
        this.message = message;
    }
}
