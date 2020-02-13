package com.waes.assignment.api.service;

import com.waes.assignment.api.domain.Diff;
import com.waes.assignment.api.repository.DiffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiffServiceImpl implements DiffService {
    private DiffRepository diffRepository;

    @Autowired
    public DiffServiceImpl(DiffRepository diffRepository) {
        this.diffRepository = diffRepository;
    }

    @Override
    public Optional<Diff> findDiffById(Long id) {
        return diffRepository.findById(id);
    }

    @Override
    public boolean addDiff(Diff diff) {
        return diffRepository.save(diff) != null;
    }
}
