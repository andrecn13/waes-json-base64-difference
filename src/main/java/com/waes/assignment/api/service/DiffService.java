package com.waes.assignment.api.service;

import com.waes.assignment.api.domain.entity.Diff;
import com.waes.assignment.api.domain.request.DiffRequest;
import com.waes.assignment.api.domain.response.DiffResultResponse;
import com.waes.assignment.api.exception.DiffNotFoundException;
import com.waes.assignment.api.exception.InvalidDiffException;

import java.util.Optional;

/**
 * Class containing methods to add a DIFF and check the differences
 * between sides of a DIFF
 */
public interface DiffService {

    /**
     * Method responsable for find differences in a DIFF
     *
     * @param id identifier of a DIFF
     * @return an object that represents a result containing offsets of differences found in a DIFF
     * @throws DiffNotFoundException when none DIFF was found by the provided identifier
     */
    Optional<DiffResultResponse> findDifferences(Long id) throws DiffNotFoundException;

    /**
     * Method responsable for find a DIFF by the provided identifier
     *
     * @param id identifier of a DIFF
     * @return an object that represents a DIFF
     */
    Optional<Diff> findDiffById(Long id);

    /**
     * Method responsable for adding a DIFF
     *
     * @param diffRequest an object that represents a DIFF
     * @return an object that represents a DIFF when it is successful persisted
     */
    Diff addDiff(DiffRequest diffRequest) throws InvalidDiffException;
}
