package com.waes.assignment.api.exceptionHandler;

import com.waes.assignment.api.domain.response.ExceptionResponse;
import com.waes.assignment.api.exception.InvalidDiffException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InvalidDiffExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidDiffException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidDiffExceptionHandler(Exception e){
        return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(e.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
