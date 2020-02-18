package com.waes.assignment.api.exception;

public class InvalidDiffException extends RuntimeException {
    public InvalidDiffException() {
        super("The provided DIFF is invalid");
    }

    public InvalidDiffException(String message) {
        super(message);
    }
}
