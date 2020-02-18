package com.waes.assignment.api.exceptionHandler;

import com.waes.assignment.api.domain.response.ExceptionResponse;
import com.waes.assignment.api.exception.DiffNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Class to configure spring bean to capture exception from {@code DiffNotFoundException}
 */
@ControllerAdvice
public class DiffNotFoundExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DiffNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleDiffNotFoundException(Exception e){
        return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
