package imoveis.api.exception.handler;

import imoveis.api.exception.custom.EmailAlreadyExistsException;
import imoveis.api.exception.custom.InvalidDateException;
import imoveis.api.exception.message.ExceptionMessage;
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
        return exceptionThrower(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ExceptionMessage> handleEmailALreadyExistsException(EmailAlreadyExistsException ex) {
        return exceptionThrower(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<ExceptionMessage> handleInvalidDateException(InvalidDateException ex) {
        return exceptionThrower(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    private ResponseEntity<ExceptionMessage> exceptionThrower(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new ExceptionMessage(status, message));
    }
}