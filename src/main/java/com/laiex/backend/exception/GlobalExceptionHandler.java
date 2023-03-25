package com.laiex.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
//    public ResponseEntity<ErrorResponse> handleConstraintViolation(SQLIntegrityConstraintViolationException ex) {
//        String errorMessage = "The username has been used, please try another one!";
//        ErrorResponse errorResponse = new DuplicateUsername(HttpStatus.CONFLICT, errorMessage);
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
//    }

}
