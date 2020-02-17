package com.waes.assignment.api.domain.response;

import java.io.Serializable;

/**
 * Class that represents a difference found
 */
public class DiffDetailResponse implements Serializable {

    private static final long serialVersionUID = -7700389396808157828L;

    /**
     * position of a difference
     */
    private int offset;

    /**
     * length of a difference
     */
    private int length;

    public DiffDetailResponse() {
    }

    public DiffDetailResponse(int offset, int length) {
        this.offset = offset;
        this.length = length;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
