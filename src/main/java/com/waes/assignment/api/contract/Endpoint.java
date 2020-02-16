package com.waes.assignment.api.contract;

import com.waes.assignment.api.binder.DiffBinder;
import com.waes.assignment.api.domain.entity.Diff;
import com.waes.assignment.api.domain.enums.DiffSideEnum;
import com.waes.assignment.api.exception.InvalidContentException;
import com.waes.assignment.api.service.DiffService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.waes.assignment.api.validator.DiffValidator.validateRequest;

@RestController
@RequestMapping("/v1/diff")
public class Endpoint {

    @Autowired
    private DiffService diffService;

    @Autowired
    private DiffBinder diffBinder;

    @RequestMapping(value = "/{id}/left", method = RequestMethod.PUT)
    public ResponseEntity<?> saveLeftDiff(@PathVariable("id") Long id, @RequestBody byte[] body) throws InvalidContentException {
        validateRequest(body);

        return ResponseEntity
                .ok(diffService.addDiff(diffBinder.buildRequest(id, body, DiffSideEnum.LEFT)));
    }

    @RequestMapping(value = "/{id}/right", method = RequestMethod.PUT)
    public ResponseEntity<?> saveRightDiff(@PathVariable("id") Long id, @RequestBody byte[] body) throws InvalidContentException {
        validateRequest(body);

        return ResponseEntity
                .ok(diffService.addDiff(diffBinder.buildRequest(id, body, DiffSideEnum.RIGHT)));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getResults(@PathVariable("id") Long id) {
        return new ResponseEntity<Optional<Diff>>(diffService.findDiffById(id), HttpStatus.OK);
    }
}
