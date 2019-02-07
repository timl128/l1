package com.example.lunch.controller;

import com.example.lunch.bean.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception e){
        return buildResponseEntity(new ErrorResponse(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse, HttpStatus status) {
        return new ResponseEntity<>(errorResponse, status);
    }
}
