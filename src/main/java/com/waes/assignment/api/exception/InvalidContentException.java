package com.waes.assignment.api.exception;

/**
 * Class to represent an exception when content is not a valid base64 data
 */
public class InvalidContentException extends Exception {
    public InvalidContentException() {
        super("Not a valid base64 content");
    }

    public InvalidContentException(String message) {
        super(message);
    }
}
