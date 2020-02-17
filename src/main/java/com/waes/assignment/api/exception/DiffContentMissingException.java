package com.waes.assignment.api.exception;

/**
 * Class to represent an exception when content missing in one or both sides
 */
public class DiffContentMissingException extends RuntimeException {
    public DiffContentMissingException() {
        super("Left or Right content is missing");
    }

    public DiffContentMissingException(String message) {
        super(message);
    }
}
