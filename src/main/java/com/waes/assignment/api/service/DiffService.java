package com.waes.assignment.api.service;

import com.waes.assignment.api.domain.entity.Diff;
import com.waes.assignment.api.domain.request.DiffRequest;

import java.util.Optional;

public interface DiffService {

    Optional<Diff> findDiffById(Long id);

    boolean addDiff(DiffRequest diffRequest);
}
