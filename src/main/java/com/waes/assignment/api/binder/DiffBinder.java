package com.waes.assignment.api.binder;

import com.waes.assignment.api.domain.enums.DiffSideEnum;
import com.waes.assignment.api.domain.request.DiffRequest;
import org.springframework.util.Base64Utils;

/**
 * Class to transform request data into an object that represents a DIFF
 */
public class DiffBinder {

    /**
     *
     * @param id identifier of a DIFF
     * @param body data in base64 encoded format that represents a JSON object
     * @param side Enum that represents a SIDE of a DIFF
     * @return an object that represents a DIFF
     */
    public DiffRequest buildRequest(Long id, byte[] body, DiffSideEnum side) {
        return new DiffRequest(id, new String(Base64Utils.decode(body)), side);
    }
}
