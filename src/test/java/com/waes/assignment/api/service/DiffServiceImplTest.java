package com.waes.assignment.api.service;

import com.waes.assignment.api.domain.entity.Diff;
import com.waes.assignment.api.domain.enums.DiffResultType;
import com.waes.assignment.api.domain.enums.DiffSideEnum;
import com.waes.assignment.api.domain.request.DiffRequest;
import com.waes.assignment.api.domain.response.DiffResultResponse;
import com.waes.assignment.api.exception.DiffContentMissingException;
import com.waes.assignment.api.exception.DiffNotFoundException;
import com.waes.assignment.api.exception.InvalidDiffException;
import com.waes.assignment.api.repository.DiffRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class DiffServiceImplTest {

    private DiffService diffService;
    private DiffRepository diffRepository;

    @Before
    public void setUp() {
        diffRepository = mock(DiffRepository.class);
        diffService = new DiffServiceImpl(diffRepository);
    }

    @Test(expected = InvalidDiffException.class)
    public void shouldFailWhenDiffIsNull() throws InvalidDiffException {
        diffService.addDiff(null);
    }

    @Test(expected = DiffNotFoundException.class)
    public void shouldFailWhenDiffNotFoundById() throws DiffNotFoundException {
        when(diffRepository.findById(eq(1L))).thenReturn(Optional.empty());

        diffService.findDifferences(1L);
    }

    @Test(expected = DiffContentMissingException.class)
    public void shouldFailWhenNoContentFound() throws DiffContentMissingException {
        when(diffRepository.findById(eq(1L))).thenReturn(Optional.of(new Diff(1L, null, null)));

        diffService.findDifferences(1L);
    }

    @Test
    public void shouldSaveDiffWithLeftContent() {
        DiffRequest diffRequest = new DiffRequest(1L, "test", DiffSideEnum.LEFT);

        when(diffRepository.findById(eq(1L))).thenReturn(Optional.empty());
        when(diffRepository.save(any(Diff.class))).thenReturn(new Diff(1L, "right", null));

        Diff diffCreated = diffService.addDiff(diffRequest);

        verify(diffRepository, times(1)).findById(eq(1L));
        assertThat(diffCreated.getId()).isSameAs(diffRequest.getId());
    }

    @Test
    public void shouldSaveDiffWithRightContent() {
        DiffRequest diffRequest = new DiffRequest(1L, "test", DiffSideEnum.RIGHT);

        when(diffRepository.findById(eq(1L))).thenReturn(Optional.empty());
        when(diffRepository.save(any(Diff.class))).thenReturn(new Diff(1L, null, "right"));

        Diff diffCreated = diffService.addDiff(diffRequest);

        verify(diffRepository, times(1)).findById(eq(1L));
        assertThat(diffCreated.getId()).isSameAs(diffRequest.getId());
    }

    @Test
    public void shouldSaveDiffWithNoContentWhenSideNull() {
        DiffRequest diffRequest = new DiffRequest(1L, "test", null);

        when(diffRepository.findById(eq(1L))).thenReturn(Optional.empty());
        when(diffRepository.save(any(Diff.class))).thenReturn(new Diff(1L, null, null));

        Diff diffCreated = diffService.addDiff(diffRequest);

        verify(diffRepository, times(1)).findById(eq(1L));
        assertThat(diffCreated.getId()).isSameAs(diffRequest.getId());
        assertThat(diffCreated.getLeft()).isNull();
        assertThat(diffCreated.getRight()).isNull();
    }

    @Test
    public void whenSameContentShouldReturnEqual() {
        when(diffRepository.findById(eq(1L))).thenReturn(Optional.of(new Diff(1L, "aaa", "aaa")));

        Optional<DiffResultResponse> diffResultResponse = diffService.findDifferences(1L);

        verify(diffRepository, times(1)).findById(eq(1L));
        assertThat(diffResultResponse).isNotNull();
        assertThat(diffResultResponse.get()).isNotNull();
        assertThat(diffResultResponse.get().getResult()).isSameAs(DiffResultType.EQUAL);
    }

    @Test
    public void whenContentHasDifferentSizesShouldReturnDifferentSizes() {
        when(diffRepository.findById(eq(1L))).thenReturn(Optional.of(new Diff(1L, "aaa", "a")));

        Optional<DiffResultResponse> diffResultResponse = diffService.findDifferences(1L);

        verify(diffRepository, times(1)).findById(eq(1L));
        assertThat(diffResultResponse).isNotNull();
        assertThat(diffResultResponse.get()).isNotNull();
        assertThat(diffResultResponse.get().getResult()).isSameAs(DiffResultType.DIFFERENT_SIZES);
    }

    @Test
    public void whenContentHasSameSizesButDifferentContentShouldReturnNotEqual() {
        when(diffRepository.findById(eq(1L))).thenReturn(Optional.of(new Diff(1L, "aaa", "bbb")));

        Optional<DiffResultResponse> diffResultResponse = diffService.findDifferences(1L);

        verify(diffRepository, times(1)).findById(eq(1L));
        assertThat(diffResultResponse).isNotNull();
        assertThat(diffResultResponse.get()).isNotNull();
        assertThat(diffResultResponse.get().getResult()).isSameAs(DiffResultType.NOT_EQUAL);
    }

}
