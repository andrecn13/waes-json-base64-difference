package com.waes.assignment.api.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Diff {

    @Id
    private Long id;
    private String value;

    public Diff() {}

    public Diff(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
