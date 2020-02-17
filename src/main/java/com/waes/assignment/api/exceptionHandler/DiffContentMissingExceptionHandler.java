package com.waes.assignment.api.exceptionHandler;

import com.waes.assignment.api.domain.response.ExceptionResponse;
import com.waes.assignment.api.exception.DiffContentMissingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Class to configure spring bean to capture exception from {@code DiffContentMissingException}
 */
@ControllerAdvice
public class DiffContentMissingExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DiffContentMissingException.class)
    public ResponseEntity<ExceptionResponse> handleDiffContentMissingException(Exception e){
        return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
