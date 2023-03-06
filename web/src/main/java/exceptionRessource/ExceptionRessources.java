package exceptionRessource;

import exception.AccountNotFoundException;
import exception.InsufficientfundsException;
import exception.TransactionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class ExceptionRessources {
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> accountException (AccountNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<Object> transactionException (TransactionNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InsufficientfundsException.class)
    public ResponseEntity<Object> fundsException (InsufficientfundsException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

