package com.microservice.HotelService.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<ApiResponse>handlerHotelException(HotelNotFoundException ex){

        ApiResponse response=ApiResponse.builder()
            .message(ex.getMessage())
            .success(true)
            .status(HttpStatus.NOT_FOUND)
            .build();

        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
    }
}
