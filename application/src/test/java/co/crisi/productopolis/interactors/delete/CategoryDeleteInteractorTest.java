package co.crisi.productopolis.interactors.delete;

import co.crisi.productopolis.boundaries.input.delete.ICategoryDeleteBoundary;
import co.crisi.productopolis.boundaries.output.ICategoryDeleteGateway;
import co.crisi.productopolis.exception.CategoryBusinessException;
import co.crisi.productopolis.exception.CategoryNotFoundException;
import co.crisi.productopolis.presenter.delete.ICategoryDeletePresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class CategoryDeleteInteractorTest {

    @Mock
    private ICategoryDeleteGateway gateway;

    @Mock
    private ICategoryDeletePresenter presenter;

    private ICategoryDeleteBoundary boundary;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        boundary = new CategoryDeleteInteractor(gateway, presenter);
    }


    @Test
    void deleteByIdWhenExistingId_shouldPrepareSuccessfulView() throws CategoryBusinessException {
        var id = 1L;

        given(gateway.existsById(id))
                .willReturn(true);
        given(presenter.prepareSuccessfulView(id))
                .willReturn(id);

        var response = boundary.deleteById(id);

        verify(gateway).existsById(id);
        verify(presenter).prepareSuccessfulView(id);
        assertThat(response)
                .isEqualTo(id);
    }

    @Test
    void deleteByIdWhenNonExistingId_shouldPrepareFailView() throws CategoryBusinessException {
        var id = 1L;
        var throwable = new CategoryNotFoundException(String.format("The category with id %d does not exists!", id));

        given(gateway.existsById(id))
                .willReturn(false);
        given(presenter.prepareFailView(any(CategoryBusinessException.class)))
                .willThrow(throwable);

        var response = catchThrowable(() -> {
            var responseId = boundary.deleteById(id);
        });

        verify(gateway).existsById(id);
        verify(presenter).prepareFailView(any(CategoryBusinessException.class));
        assertThat(response)
                .isEqualTo(throwable);
    }

}