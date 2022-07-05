package com.rmuti.guidemap.backend.business;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rmuti.guidemap.backend.exception.BaseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.Data;


@ControllerAdvice
public class ErrorAdviser {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handlerException(BaseException e) {
        ErrorResponse response = new ErrorResponse();
        response.setError(e.getMessage());
        response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED); 
        
    }
    
    @Data
    public static class ErrorResponse {

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime timestamp = LocalDateTime.now();

        private int status;

        private String error;
        
    }
}
