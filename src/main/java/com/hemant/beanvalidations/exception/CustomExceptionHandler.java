package com.hemant.beanvalidations.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler
{

    @ExceptionHandler(InvalidInputException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(InvalidInputException ex)
    {
        ErrorResponse error = new ErrorResponse(HttpStatus.LENGTH_REQUIRED.value(),ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.LENGTH_REQUIRED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
    @ExceptionHandler(Exception.class) //Global exception handling
    public final ResponseEntity<ErrorResponse> handleUnAuthenticException(Exception ex)
    {
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class) //Global exception handling
    public final ResponseEntity<ErrorResponse> handleUnAuthenticException(HttpMediaTypeNotAcceptableException ex)
    {
        ErrorResponse error = new ErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}