package com.waes.assignment.api.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity object that represents a DIFF
 */
@Entity
@Table(name = "DIFF")
public class Diff {

    /**
     * Identifier of a DIFF
     */
    @Id
    @Column(name = "ID")
    private Long id;

    /**
     * Left content of a DIFF
     */
    @Column(name = "LEFT_VALUE")
    private String left;

    /**
     * Right content of a DIFF
     */
    @Column(name = "RIGHT_VALUE")
    private String right;

    public Diff() {}

    public Diff(Long id) {
        this.id = id;
    }

    public Diff(Long id, String left, String right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
}
