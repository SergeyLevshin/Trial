package ru.levshin.trial.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.levshin.trial.exception.PaymentException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PaymentException.class)
    protected <T extends PaymentException> ResponseEntity<String> exceptionHandler(T exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // there should be some code for different exceptions
}
