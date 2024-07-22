package com.assignment.bidding.config.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
public class ApiException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String message;

}