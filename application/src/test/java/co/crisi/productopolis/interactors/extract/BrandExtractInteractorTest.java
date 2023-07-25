package co.crisi.productopolis.interactors.extract;

import co.crisi.productopolis.boundaries.input.extract.IBrandExtractBoundary;
import co.crisi.productopolis.boundaries.output.IBrandExtractGateway;
import co.crisi.productopolis.domain.factory.IBrandFactory;
import co.crisi.productopolis.domain.factory.impl.BrandFactory;
import co.crisi.productopolis.exception.BrandBusinessException;
import co.crisi.productopolis.exception.BrandNotFoundException;
import co.crisi.productopolis.model.response.BrandResponse;
import co.crisi.productopolis.model.response.mapper.BrandMapper;
import co.crisi.productopolis.presenter.extract.IBrandExtractPresenter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


class BrandExtractInteractorTest {

    private final BrandMapper mapper = Mappers.getMapper(BrandMapper.class);

    @Mock
    private IBrandExtractPresenter presenter;

    @Mock
    private IBrandExtractGateway gateway;

    private IBrandExtractBoundary boundary;

    private final IBrandFactory factory = new BrandFactory();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        boundary = new BrandExtractInteractor(mapper, presenter, gateway);
    }

    @Test
    void getByIdWhenExistingBrand_shouldPrepareSuccessfulView() throws BrandBusinessException {
        var id = 1L;

        var brand = factory.create(id, "Apple", "Great Company", new ArrayList<>());

        given(gateway.existsById(id))
                .willReturn(true);

        given(gateway.getById(id))
                .willReturn(brand);

        given(presenter.prepareSuccessfulView(any(BrandResponse.class)))
                .willReturn(mapper.map(brand));

        var response = boundary.getById(id);

        verify(gateway).existsById(id);
        verify(gateway).getById(id);
        verify(presenter)
                .prepareSuccessfulView(any(BrandResponse.class));

        assertThat(response)
                .isNotNull()
                .extracting(BrandResponse::id, BrandResponse::name, BrandResponse::description)
                .contains(brand.getId(), brand.getName(), brand.getDescription());

    }

    @Test
    void getByIdWhenNonExistingBrand_shouldPrepareFailView() throws BrandBusinessException {
        var id = 1L;

        var throwable = new BrandNotFoundException(String.format("The brand with id %d was not found!", id));

        given(gateway.existsById(id))
                .willReturn(false);

        given(presenter.prepareFailView(any(BrandBusinessException.class)))
                .willThrow(throwable);

        var response = catchThrowable(() -> {
            var brandResponse = boundary.getById(id);
        });

        verify(gateway).existsById(id);
        verify(presenter)
                .prepareFailView(any(BrandBusinessException.class));

        assertThat(response)
                .isNotNull()
                .isInstanceOf(BrandNotFoundException.class)
                .isEqualTo(throwable);

    }

    @Test
    void getAllWhenExistingBrands_returnThemAll() {
        var brand = factory.create(1L, "Perficient", "A Great Company", new ArrayList<>());
        var brandTwo = factory.create(2L, "PSL", "A Great Company", new ArrayList<>());

        var brands = List.of(brand, brandTwo);
        var compare = mapper.map(brands);
        given(gateway.getAll())
                .willReturn(brands);
        given(presenter.prepareSuccessfulView(any(List.class)))
                .willReturn(mapper.map(brands));

        var response = boundary.getAll();

        verify(gateway).getAll();

        assertThat(response)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(compare);

    }

}