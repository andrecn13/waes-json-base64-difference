package com.waes.assignment.api.domain.response;

import java.io.Serializable;

/**
 * Class that represents an exception thrown
 */
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 3098887876522623030L;

    /**
     * Message explaining the reason of exception
     */
    private String message;

    public ExceptionResponse() {
    }

    public ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
