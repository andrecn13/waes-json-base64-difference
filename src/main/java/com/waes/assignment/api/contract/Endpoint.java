package com.waes.assignment.api.contract;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class Endpoint {

    @RequestMapping(value = "/diff/left", method = RequestMethod.POST)
    public ResponseEntity<?> saveLeft() {

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
