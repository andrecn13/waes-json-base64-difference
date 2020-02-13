package com.waes.assignment.api.service;

import com.waes.assignment.api.domain.Diff;

import java.util.Optional;

public interface DiffService {

    Optional<Diff> findDiffById(Long id);

    boolean addDiff(Diff diff);
}
