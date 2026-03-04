package com.patrick.finance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ResponseError> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.CONFLICT);


        // Metodo que usamos no modo inicial
//        ResponseError responde = new ResponseError(
//                ex.getMessage(),
//                HttpStatus.BAD_REQUEST,
//                LocalDateTime.now());
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responde);

    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseError> handleUserNotFound(UserNotFoundException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> trataException(Exception ex){
        ResponseError responde = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responde);
    }

    private ResponseEntity<ResponseError> buildResponse(String message, HttpStatus status) {
        ResponseError error = new ResponseError(message, status, LocalDateTime.now());
        return ResponseEntity.status(status).body(error);
    }
}
