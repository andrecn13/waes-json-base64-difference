package com.waes.assignment.api.validator;

import com.waes.assignment.api.exception.InvalidContentException;

import java.util.Base64;

/**
 * Class that provided methods to validate a request content
 */
public class DiffValidator {

    /**
     * Method that process all validations in a base64 JSON data
     *
     * @param body string that represents a base64 JSON data
     * @throws InvalidContentException when the content provided is not a valid base64
     */
    public static void validateRequest(byte[] body) throws InvalidContentException {
        if (body == null || body.length == 0) throw new InvalidContentException();
        if (!isValidContent(body)) throw new InvalidContentException();
    }

    private static boolean isValidContent(byte[] body) throws InvalidContentException{
        try {
            Base64.getDecoder().decode(body);
        }catch (Exception e) {
            throw new InvalidContentException();
        }

        return true;
    }
}
