package co.crisi.productopolis.interactors.update;

import co.crisi.productopolis.boundaries.input.update.BrandUpdateBoundary;
import co.crisi.productopolis.boundaries.output.IBrandUpdateGateway;
import co.crisi.productopolis.domain.BrandRequestMother;
import co.crisi.productopolis.domain.factory.IBrandFactory;
import co.crisi.productopolis.domain.factory.impl.BrandFactory;
import co.crisi.productopolis.exception.BrandBusinessException;
import co.crisi.productopolis.exception.BrandNotFoundException;
import co.crisi.productopolis.model.request.BrandUpdateRequest;
import co.crisi.productopolis.model.response.mapper.BrandMapper;
import co.crisi.productopolis.presenter.update.IBrandUpdatePresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

class BrandUpdateInteractorTest {


    private final IBrandFactory factory = new BrandFactory();

    @Mock
    private IBrandUpdateGateway gateway;

    @Mock
    private IBrandUpdatePresenter presenter;

    private final BrandMapper mapper = Mappers.getMapper(BrandMapper.class);

    private BrandUpdateBoundary boundary;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        boundary = new BrandUpdateInteractor(factory, gateway, presenter, mapper);
    }

    @Test
    void updateWhenExistingBrand_shouldPrepareSuccessfulView() throws BrandBusinessException {
        var id = 1L;
        var request = BrandRequestMother.random();
        var updateRequest = new BrandUpdateRequest(id, request);
        var brandUpdate = factory.create(id, request.name(), request.description());
        var brandUpdateResponse = mapper.map(brandUpdate);

        given(gateway.existsById(id))
                .willReturn(true);

        given(gateway.update(id, brandUpdate))
                .willReturn(brandUpdate);

        given(presenter.prepareSuccessfulView(brandUpdateResponse))
                .willReturn(brandUpdateResponse);

        var response = boundary.update(updateRequest);

        verify(gateway).existsById(id);
        verify(gateway).update(id, brandUpdate);
        verify(presenter).prepareSuccessfulView(brandUpdateResponse);

        assertThat(response)
                .isNotNull()
                .isEqualTo(brandUpdateResponse);
    }

    @Test
    void updateWhenNonExistingBrand_shouldPrepareFailView() throws BrandBusinessException {
        var id = 1L;
        var request = BrandRequestMother.random();
        var updateRequest = new BrandUpdateRequest(id, request);
        var brandUpdate = factory.create(id, request.name(), request.description());
        var brandUpdateResponse = mapper.map(brandUpdate);
        var throwable = new BrandNotFoundException(
                String.format("The brand with id %d does not exists!", id));

        given(gateway.existsById(id))
                .willReturn(false);

        given(presenter.prepareFailView(any(BrandBusinessException.class)))
                .willThrow(throwable);

        var response = catchThrowable(() -> {
            var brandResponse = boundary.update(updateRequest);
        });

        verify(gateway).existsById(id);
        verify(presenter).prepareFailView(any(BrandBusinessException.class));

        assertThat(response)
                .isInstanceOf(BrandNotFoundException.class)
                .isEqualTo(throwable);
    }

}