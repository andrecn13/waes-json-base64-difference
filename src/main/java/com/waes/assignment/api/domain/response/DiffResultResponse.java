package com.waes.assignment.api.domain.response;

import com.waes.assignment.api.domain.enums.DiffResultType;

import java.io.Serializable;
import java.util.List;

/**
 * Class that represents a result of differences found
 */
public class DiffResultResponse implements Serializable {
    private static final long serialVersionUID = -6063894655565836541L;

    /**
     * Type of result
     */
    private DiffResultType result;

    /**
     * List of all differences found
     */
    private List<DiffDetailResponse> details;

    public DiffResultResponse() {
    }

    public DiffResultResponse(DiffResultType result) {
        this.result = result;
    }

    public DiffResultResponse(DiffResultType result, List<DiffDetailResponse> details) {
        this.result = result;
        this.details = details;
    }

    public DiffResultType getResult() {
        return result;
    }

    public void setResult(DiffResultType result) {
        this.result = result;
    }

    public List<DiffDetailResponse> getDetails() {
        return details;
    }

    public void setDetails(List<DiffDetailResponse> details) {
        this.details = details;
    }
}
