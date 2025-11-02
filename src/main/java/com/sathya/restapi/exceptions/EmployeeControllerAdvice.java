package com.sathya.restapi.exceptions;

import java.security.PublicKey;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sathya.restapi.models.ApiErrorResponse;

@RestControllerAdvice
public class EmployeeControllerAdvice {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(EmployeeNotFoundException ex) {
       
    	ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
       
        apiErrorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        apiErrorResponse.setMessage(ex.getMessage());
        apiErrorResponse.setDateTime(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .header("error", "the employee not found")
                             .body(apiErrorResponse);
       
        
 
    }
}
























