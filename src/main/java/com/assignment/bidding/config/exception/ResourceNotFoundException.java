package com.assignment.bidding.config.exception;

import com.assignment.bidding.config.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {
    public ResourceNotFoundException(String resource, Long id) {
        super(HttpStatus.NOT_FOUND, String.format("%s with id=%d not found", resource, id));
    }

}