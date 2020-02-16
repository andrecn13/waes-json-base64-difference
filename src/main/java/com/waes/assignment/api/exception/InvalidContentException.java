package com.waes.assignment.api.exception;

public class InvalidContentException extends Exception {
    public InvalidContentException() {
        super("Not a valid base64 content");
    }

    public InvalidContentException(String message) {
        super(message);
    }
}
