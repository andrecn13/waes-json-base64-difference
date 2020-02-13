package com.waes.assignment.api.contract;

import com.waes.assignment.api.binder.DiffBinder;
import com.waes.assignment.api.domain.Diff;
import com.waes.assignment.api.domain.request.DiffRequest;
import com.waes.assignment.api.service.DiffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class Endpoint {

    @Autowired
    private DiffService diffService;

    @Autowired
    private DiffBinder diffBinder;

    @RequestMapping(value = "/diff/{id}/left", method = RequestMethod.POST)
    public ResponseEntity<?> saveLeftDiff(@PathVariable("id") Long id,
                                      @RequestBody @Valid DiffRequest diffRequest) {
        try {
            return ResponseEntity
                    .ok(diffService.addDiff(diffBinder.requestToModel(diffRequest, id)));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }
    }

    @RequestMapping(value = "/diff/left/1", method = RequestMethod.GET)
    public ResponseEntity<?> get() {
        return new ResponseEntity<Optional<Diff>>(diffService.findDiffById(1l), HttpStatus.OK);
    }
}
