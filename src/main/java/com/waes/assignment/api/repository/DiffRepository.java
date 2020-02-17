package com.waes.assignment.api.repository;

import com.waes.assignment.api.domain.entity.Diff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiffRepository extends CrudRepository<Diff, Long> {

    /**
     * Find a DIFF by the provided identifier
     * @param id identifier of a DIFF
     * @return a DIFF object
     */
    Optional<Diff> findById(Long id);
}
