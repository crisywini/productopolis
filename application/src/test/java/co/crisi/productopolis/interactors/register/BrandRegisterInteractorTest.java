package co.crisi.productopolis.interactors.register;

import co.crisi.productopolis.boundaries.input.register.IBrandRegisterBoundary;
import co.crisi.productopolis.boundaries.output.IBrandRegisterGateway;
import co.crisi.productopolis.domain.BrandRequestMother;
import co.crisi.productopolis.domain.factory.IBrandFactory;
import co.crisi.productopolis.domain.factory.impl.BrandFactory;
import co.crisi.productopolis.exception.BrandBusinessException;
import co.crisi.productopolis.exception.RepeatedBrandException;
import co.crisi.productopolis.model.response.mapper.BrandMapper;
import co.crisi.productopolis.presenter.register.IBrandRegisterPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.assertj.core.api.Assertions.assertThat;


class BrandRegisterInteractorTest {

    @Mock
    private IBrandRegisterPresenter presenter;

    @Mock
    private IBrandRegisterGateway gateway;

    private final BrandMapper mapper = Mappers.getMapper(BrandMapper.class);

    private final IBrandFactory factory = new BrandFactory();

    private IBrandRegisterBoundary boundary;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        boundary = new BrandRegisterInteractor(presenter, factory, gateway, mapper);
    }


    @Test
    void createWhenNotExistingBrand_shouldCallGatewaySave() throws BrandBusinessException {

        var request = BrandRequestMother.random();

        var brandCreated = mapper.map(request);

        given(gateway.existsById(request.id()))
                .willReturn(false);

        given(presenter.prepareSuccessfulView(mapper.map(brandCreated)))
                .willReturn(mapper.map(brandCreated));

        var response = boundary.create(request);

        verify(gateway).existsById(request.id());
        verify(gateway).save(brandCreated);
        verify(presenter).prepareSuccessfulView(response);

    }

    @Test
    void createWhenExistingBrand_shouldCallPresenterFailView() throws BrandBusinessException {

        var request = BrandRequestMother.random();

        var throwable = new RepeatedBrandException(String.format("The brand with id %d already exists!", request.id()));
        given(gateway.existsById(request.id()))
                .willReturn(true);

        given(presenter.prepareFailView(any(RepeatedBrandException.class)))
                .willThrow(throwable);

        var response = catchThrowable(() -> {
            var brandResponse = boundary.create(request);
        });

        verify(gateway).existsById(request.id());
        verify(presenter).prepareFailView(any(RepeatedBrandException.class));

        assertThat(response)
                .isInstanceOf(RepeatedBrandException.class)
                .isEqualTo(throwable);

    }

}