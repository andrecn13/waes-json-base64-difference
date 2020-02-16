package com.waes.assignment.api.domain.request;

import com.waes.assignment.api.domain.enums.DiffSideEnum;

import javax.validation.constraints.NotNull;

public class DiffRequest {

    private long id;
    private String value;
    private DiffSideEnum side;

    public DiffRequest() {}

    public DiffRequest(long id, String value, DiffSideEnum side) {
        this.id = id;
        this.value = value;
        this.side = side;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DiffSideEnum getSide() {
        return side;
    }

    public void setSide(DiffSideEnum side) {
        this.side = side;
    }
}
