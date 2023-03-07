package com.jpmorgan.chase.exceptions;

public class HealthCheckFailedException extends Exception {
    public HealthCheckFailedException(String message) {
        super(message);
    }
}
