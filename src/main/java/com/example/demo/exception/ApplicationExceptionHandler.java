package com.example.demo.exception;


import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;




@RestControllerAdvice
public class ApplicationExceptionHandler {


    @ResponseStatus(HttpStatus.CONFLICT) //posts duplicate when in entity is unique
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    String handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex){
        return "Duplicates not allowed";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    String handlerRecordNotFoundException(RecordNotFoundException ex){
        return ex.getMessage(); //in service will be specified
    }

    @ResponseStatus(HttpStatus.NOT_FOUND) //get not existing record
    @ExceptionHandler(EmptyResultDataAccessException.class)
    String handlerEmptyResultDataAccessException(EmptyResultDataAccessException ex){
        return "Record not found";
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>("Request not valid due to validation error.", HttpStatus.BAD_REQUEST);
       //return new ResponseEntity<>("Request not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
