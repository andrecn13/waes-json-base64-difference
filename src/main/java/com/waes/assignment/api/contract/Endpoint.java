package com.waes.assignment.api.contract;

import com.waes.assignment.api.binder.DiffBinder;
import com.waes.assignment.api.domain.enums.DiffSideEnum;
import com.waes.assignment.api.domain.response.DiffResultResponse;
import com.waes.assignment.api.exception.DiffNotFoundException;
import com.waes.assignment.api.exception.InvalidContentException;
import com.waes.assignment.api.service.DiffService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.waes.assignment.api.validator.DiffValidator.validateRequest;

@Api(value = "/v1/diff", tags = {"diff"})
@RestController
@RequestMapping("/v1/diff")
public class Endpoint {

    @Autowired
    private DiffService diffService;

    @Autowired
    private DiffBinder diffBinder;

    /**
     * Endpoint to persist a DIFF in left side
     * @param id identifier of a DIFF
     * @param body data in base64 encoded format that represents a JSON object
     * @return true when data is successful persisted
     * @throws InvalidContentException when content is in invalid format
     */
    @ApiOperation(value = "Save LEFT content of DIFF by ID", response = Boolean.class)
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Not a valid base64 content")})
    @RequestMapping(value = "/{id}/left", method = RequestMethod.PUT)
    public ResponseEntity<?> saveLeftDiff(@ApiParam(value = "identifier of a diff", required = true) @PathVariable("id") Long id,
                                          @RequestBody byte[] body) throws InvalidContentException {

        // Validate requested content
        validateRequest(body);

        return ResponseEntity
                .ok(diffService.addDiff(diffBinder.buildRequest(id, body, DiffSideEnum.LEFT)));
    }

    /**
     * Endpoint to persist a DIFF in right side
     * @param id identifier of a DIFF
     * @param body data in base64 encoded format that represents a JSON object
     * @return true when data is successful persisted
     * @throws InvalidContentException when content is in invalid format
     */
    @ApiOperation(value = "Save RIGHT content of DIFF by ID", response = Boolean.class)
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Not a valid base64 content")})
    @RequestMapping(value = "/{id}/right", method = RequestMethod.PUT)
    public ResponseEntity<?> saveRightDiff(@ApiParam(value = "identifier of a diff", required = true) @PathVariable("id") Long id,
                                           @RequestBody byte[] body) throws InvalidContentException {

        // Validate requested content
        validateRequest(body);

        return ResponseEntity
                .ok(diffService.addDiff(diffBinder.buildRequest(id, body, DiffSideEnum.RIGHT)));
    }

    /**
     * Endpoint to check the differences between sides of a DIFF
     * @param id identifier of a DIFF
     * @return an object showing details where differences are
     * @throws DiffNotFoundException when none diff were found with provided identifier
     */
    @ApiOperation(value = "Get differences of DIFF by ID", response = DiffResultResponse.class)
    @ApiResponses(value = { @ApiResponse(code = 400, message = "None diff found with provided ID")})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getResults(@ApiParam(value = "identifier of a diff", required = true) @PathVariable("id") Long id) throws DiffNotFoundException {
        return new ResponseEntity<Optional<DiffResultResponse>>(diffService.findDifferences(id), HttpStatus.OK);
    }
}
