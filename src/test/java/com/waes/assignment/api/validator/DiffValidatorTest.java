package com.waes.assignment.api.validator;

import com.waes.assignment.api.exception.InvalidContentException;
import org.junit.Test;

public class DiffValidatorTest {

    @Test(expected = InvalidContentException.class)
    public void shouldFailWhenContentIsNull() throws InvalidContentException {
        DiffValidator.validateRequest(null);
    }

    @Test(expected = InvalidContentException.class)
    public void shouldFailWhenContentIsNotValidBase64() throws InvalidContentException {
        DiffValidator.validateRequest("eyJhbmRyaSI6IHRydWV9f".getBytes());
    }
}
