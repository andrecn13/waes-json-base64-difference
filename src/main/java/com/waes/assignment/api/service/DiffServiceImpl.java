package com.waes.assignment.api.service;

import com.waes.assignment.api.domain.entity.Diff;
import com.waes.assignment.api.domain.enums.DiffResultType;
import com.waes.assignment.api.domain.enums.DiffSideEnum;
import com.waes.assignment.api.domain.request.DiffRequest;
import com.waes.assignment.api.domain.response.DiffDetailResponse;
import com.waes.assignment.api.domain.response.DiffResultResponse;
import com.waes.assignment.api.exception.DiffContentMissingException;
import com.waes.assignment.api.exception.DiffNotFoundException;
import com.waes.assignment.api.repository.DiffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class that implement methods required by {@code DiffService} interface
 */
@Service
public class DiffServiceImpl implements DiffService {
    private DiffRepository diffRepository;

    @Autowired
    public DiffServiceImpl(DiffRepository diffRepository) {
        this.diffRepository = diffRepository;
    }

    @Override
    public Optional<DiffResultResponse> findDifferences(Long id) throws DiffNotFoundException {
        Diff diff = diffRepository.findById(id).orElse(null);

        return buildDifferenceResults(diff);
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

    /**
     * Method that valid and process differences between sides of a DIFF
     *
     * @param diff an object that represents a DIFF
     * @return an object that represents a result of differences between sides of a DIFF
     */
    private Optional<DiffResultResponse> buildDifferenceResults(Diff diff) {
        if(diff == null) throw new DiffNotFoundException();
        if(diff.getLeft() == null || diff.getRight() == null) throw new DiffContentMissingException();

        if(isEqualContent(diff)) return Optional.of(new DiffResultResponse(DiffResultType.EQUAL));
        if(isContentDifferentSize(diff)) return Optional.of(new DiffResultResponse(DiffResultType.DIFFERENT_SIZES));

        return Optional.of(new DiffResultResponse(DiffResultType.NOT_EQUAL, compileDifferences(diff)));
    }

    /**
     * Method that compare both sides of a DIFF and process their differences
     *
     * @param diff an object that represents a DIFF
     * @return List of differences contaning offset and length
     */
    private List<DiffDetailResponse> compileDifferences(Diff diff) {
        List<DiffDetailResponse> details = new ArrayList<>();

        byte[] leftContent = diff.getLeft().getBytes();
        byte[] rightContent = diff.getRight().getBytes();

        int diffLenght = 1;
        int diffIndex = 0;
        int diffOffset = 0;

        boolean diffFound = false;

        while (diffIndex < leftContent.length) {
            if (leftContent[diffIndex] == rightContent[diffIndex]) {

                // If content are equals, just move to next index
                if (!diffFound) {
                    diffIndex++;
                    continue;
                }

                // Once found, add to list
                details.add(new DiffDetailResponse(diffOffset, diffLenght));

                diffFound = false;
                diffLenght = 1;
                diffIndex++;

                continue;
            }

            // If content are not equals, set offset of difference and move to next index
            if (!diffFound) {
                diffFound = true;
                diffOffset = diffIndex;
                diffIndex++;
                continue;
            }

            diffIndex++;
            diffLenght++;
        }

        return details;
    }

    /**
     * Method to check if both sides of a DIFF are equal
     *
     * @param diff an object that represents a DIFF content
     * @return true when both sides are equal
     */
    private boolean isEqualContent(Diff diff) {
        return diff.getLeft().equals(diff.getRight());
    }

    /**
     * Method to check if both sides of a DIFF has different sizes
     *
     * @param diff an object that represents a DIFF content
     * @return true when both sides are not same size
     */
    private boolean isContentDifferentSize(Diff diff) {
        return diff.getLeft().length() != diff.getRight().length();
    }

}
