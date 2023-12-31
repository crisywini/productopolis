package co.crisi.productopolis.interactors.delete;

import co.crisi.productopolis.boundaries.input.delete.IBrandDeleteBoundary;
import co.crisi.productopolis.boundaries.output.IBrandDeleteGateway;
import co.crisi.productopolis.exception.BrandBusinessException;
import co.crisi.productopolis.exception.BrandNotFoundException;
import co.crisi.productopolis.presenter.delete.IBrandDeletePresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class BrandDeleteInteractorTest {

    @Mock
    private IBrandDeleteGateway gateway;

    @Mock
    private IBrandDeletePresenter presenter;


    private IBrandDeleteBoundary boundary;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        boundary = new BrandDeleteInteractor(gateway, presenter);
    }

    @Test
    void deleteByIdWhenExistingBrand_shouldPrepareSuccessfulView() throws BrandBusinessException {
        var id = 1L;
        given(gateway.existsById(id))
                .willReturn(true);

        given(presenter.prepareSuccessfulView(id))
                .willReturn(id);

        var idDeleted = boundary.deleteById(id);

        verify(gateway).existsById(id);
        verify(presenter).prepareSuccessfulView(id);

        assertThat(idDeleted)
                .isEqualTo(id);

    }

    @Test
    void deleteByIdWhenNonExistingBrand_shouldPrepareFailView() throws BrandBusinessException {
        var id = 1L;
        var throwable = new BrandNotFoundException(String.format("The brand with id %d was not found!", id));
        given(gateway.existsById(id))
                .willReturn(false);

        given(presenter.prepareFailView(any(BrandBusinessException.class)))
                .willThrow(throwable);

        var response = catchThrowable(() -> {
            var idDeleted = boundary.deleteById(id);
        });

        verify(gateway).existsById(id);
        verify(presenter).prepareFailView(any(BrandBusinessException.class));

        assertThat(response)
                .isInstanceOf(BrandNotFoundException.class)
                .isEqualTo(throwable);

    }


}