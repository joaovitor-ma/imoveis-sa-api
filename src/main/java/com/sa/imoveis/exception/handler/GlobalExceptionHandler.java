package com.sa.imoveis.exception.handler;

import com.sa.imoveis.exception.custom.EmailAlreadyExistsException;
import com.sa.imoveis.exception.message.ExceptionMessage;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionMessage> handleNoSuchElementException(NoSuchElementException ex) {
        return exceptionThrower(HttpStatus.NOT_FOUND, "This id could not be found.");
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ExceptionMessage> handleEmailALreadyExistsException(EmailAlreadyExistsException ex) {
        return exceptionThrower(HttpStatus.CONFLICT, "This email is already in use.");
    }

    private ResponseEntity<ExceptionMessage> exceptionThrower(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new ExceptionMessage(status, message));
    }
}