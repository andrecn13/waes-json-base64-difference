package com.waes.assignment.api.domain.request;

import javax.validation.constraints.NotNull;

public class DiffRequest {

    @NotNull
    private String value;

    public DiffRequest() {}

    public DiffRequest(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
