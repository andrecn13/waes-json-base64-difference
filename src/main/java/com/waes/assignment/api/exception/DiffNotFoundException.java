package com.waes.assignment.api.exception;

/**
 * Class to represent an exception when a DIFF was not found with the provided identifier
 */
public class DiffNotFoundException extends RuntimeException {
    public DiffNotFoundException() {
        super("None diff found with provided ID");
    }

    public DiffNotFoundException(String message) {
        super(message);
    }
}
