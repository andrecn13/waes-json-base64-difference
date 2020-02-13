package com.waes.assignment.api.binder;

import com.waes.assignment.api.domain.Diff;
import com.waes.assignment.api.domain.request.DiffRequest;

import java.io.Serializable;

public class DiffBinder implements Serializable {
    private static final long serialVersionUID = 3289010654623278039L;

    public Diff requestToModel(DiffRequest request, Long id) {
        return new Diff(id, request.getValue());
    }
}
