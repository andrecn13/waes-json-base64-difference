package com.waes.assignment.api.validator;

import com.waes.assignment.api.exception.InvalidContentException;

import java.util.Base64;

public class DiffValidator {

    public static void validateRequest(byte[] body) throws InvalidContentException {
        try {
            Base64.getDecoder().decode(body);
        }catch (Exception e) {
            throw new InvalidContentException();
        }
    }
}
