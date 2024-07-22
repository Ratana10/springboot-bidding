package com.assignment.bidding.config.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice

public class GlobalException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValid(MethodArgumentNotValidException ex, HttpServletRequest request){
        List<String> errors = new ArrayList<>();
        ex.getAllErrors()
                .forEach(err -> errors.add(err.getDefaultMessage()));
        Map<String ,List<String > > result = new HashMap<>();
        result.put("errors", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    @ExceptionHandler(BidAmountTooLowException.class)
    public ResponseEntity<?> handleBidAmountTooLowException(BidAmountTooLowException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(SessionEndedException.class)
    public ResponseEntity<?> handleSessionEndedException(SessionEndedException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createErrorResponse(ex.getMessage()));
    }

    private Map<String, String> createErrorResponse(String message) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", message);
        return errorResponse;
    }
}
