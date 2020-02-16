package com.waes.assignment.api.binder;

import com.waes.assignment.api.domain.enums.DiffSideEnum;
import com.waes.assignment.api.domain.request.DiffRequest;
import org.springframework.util.Base64Utils;

public class DiffBinder {

    public DiffRequest buildRequest(Long id, byte[] body, DiffSideEnum side) {
        return new DiffRequest(id, new String(Base64Utils.decode(body)), side);
    }
}
