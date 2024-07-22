package com.assignment.bidding.config.exception;

public class SessionEndedException extends RuntimeException {
    public SessionEndedException(String message) {
        super(message);
    }
}