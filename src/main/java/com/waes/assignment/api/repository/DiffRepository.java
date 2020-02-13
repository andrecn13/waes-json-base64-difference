package com.waes.assignment.api.repository;

import com.waes.assignment.api.domain.Diff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiffRepository extends CrudRepository<Diff, Long> {

    Optional<Diff> findById(Long id);
}
