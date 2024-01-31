package com.faboda.services.location.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<ApiResponse> handleInvalidArgument(InvalidArgumentException ex) {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST, "Invalid argument", ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> handleNotFoundException(NotFoundException ex){
        ApiResponse apiResponse = new ApiResponse(HttpStatus.NOT_FOUND, "Not found exception",ex.getMessage());
        return  new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }



}