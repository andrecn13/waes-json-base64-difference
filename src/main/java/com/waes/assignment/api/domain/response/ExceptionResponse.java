package com.waes.assignment.api.domain.response;

import java.io.Serializable;

public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 3098887876522623030L;

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
