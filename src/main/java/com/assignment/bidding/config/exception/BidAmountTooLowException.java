package com.assignment.bidding.config.exception;

public class BidAmountTooLowException extends RuntimeException {
    public BidAmountTooLowException(String message) {
        super(message);
    }
}