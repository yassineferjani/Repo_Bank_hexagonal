package exceptionRessource;

import exception.AccountNotFoundException;
import exception.InsufficientfundsException;
import exception.TransactionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.Clock;
import java.time.OffsetDateTime;

@RestControllerAdvice

public class ExceptionRessources {
    private final Clock clock;

    public ExceptionRessources(final Clock clock) {
        this.clock = clock;
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handle (AccountNotFoundException exception, ServletWebRequest request){
        return ApiError.builder()
                .label(HttpStatus.NOT_FOUND.getReasonPhrase())
                .path(request.getRequest().getRequestURI())
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(OffsetDateTime.now(clock))
                .message(exception.getMessage())
                .build();
    }
    @ExceptionHandler(TransactionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handle (TransactionNotFoundException exception, ServletWebRequest request){
        return ApiError.builder()
                .label(HttpStatus.NOT_FOUND.getReasonPhrase())
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getRequest().getRequestURI())
                .message(exception.getMessage())
                .timestamp(OffsetDateTime.now(clock))
                .build();
    }
    @ExceptionHandler(InsufficientfundsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handle (InsufficientfundsException exception, ServletWebRequest request){
        return ApiError.builder()
                .timestamp(OffsetDateTime.now(clock))
                .label(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(exception.getMessage())
                .path(request.getRequest().getRequestURI())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handle(Exception exception, ServletWebRequest request) {
        return ApiError.builder()
                .timestamp(OffsetDateTime.now(clock))
                .path(request.getRequest().getRequestURI())
                .message(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .label(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handle(MethodArgumentNotValidException exception, ServletWebRequest request) {
        return ApiError.builder()
                .timestamp(OffsetDateTime.now(clock))
                .path(request.getRequest().getRequestURI())
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .label(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
    }
}

