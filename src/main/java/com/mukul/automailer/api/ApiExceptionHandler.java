package com.mukul.automailer.api;

import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String, String> invalidRequest(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldErrors().stream()
                .findFirst().map(error -> error.getField() + ": " + error.getDefaultMessage())
                .orElse("Invalid request");
        return Map.of("error", message);
    }

    @ExceptionHandler(MailException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    Map<String, String> mailFailure(MailException exception) {
        return Map.of("error", "Email could not be sent. Check Gmail configuration and the app password.");
    }
}
