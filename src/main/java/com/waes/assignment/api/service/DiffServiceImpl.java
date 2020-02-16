package com.waes.assignment.api.service;

import com.waes.assignment.api.domain.entity.Diff;
import com.waes.assignment.api.domain.enums.DiffSideEnum;
import com.waes.assignment.api.domain.request.DiffRequest;
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
    public boolean addDiff(DiffRequest request) {
        Diff diff = diffRepository.findById(request.getId()).orElse(new Diff((request.getId())));

        diff.setLeft(DiffSideEnum.LEFT.equals(request.getSide()) ? request.getValue() : diff.getLeft());
        diff.setRight(DiffSideEnum.RIGHT.equals(request.getSide()) ? request.getValue() : diff.getRight());

        return diffRepository.save(diff) != null;
    }

}
