package co.crisi.productopolis.interactors.extract;

import co.crisi.productopolis.boundaries.input.extract.IProductExtractBoundary;
import co.crisi.productopolis.boundaries.output.IProductExtractGateway;
import co.crisi.productopolis.domain.objectmother.ProductMother;
import co.crisi.productopolis.exception.ProductBusinessException;
import co.crisi.productopolis.exception.ProductNotFoundException;
import co.crisi.productopolis.model.response.mapper.ProductMapper;
import co.crisi.productopolis.presenter.extract.IProductExtractPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class ProductExtractInteractorTest {

    @Mock
    private IProductExtractGateway gateway;

    @Mock
    private IProductExtractPresenter presenter;

    private IProductExtractBoundary boundary;

    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        boundary = new ProductExtractInteractor(gateway, presenter);
    }

    @Test
    void getByIdWhenExistingProduct_shouldPrepareSuccessfulView() throws ProductBusinessException {
        var product = ProductMother.random();
        var id = product.getId();
        var productResponse = mapper.map(product);
        given(gateway.existsById(id))
                .willReturn(true);
        given(gateway.getById(id))
                .willReturn(product);
        given(presenter.prepareSuccessfulView(productResponse))
                .willReturn(productResponse);

        var response = boundary.getById(id);

        verify(gateway).existsById(id);
        verify(gateway).getById(id);
        verify(presenter).prepareSuccessfulView(productResponse);
        assertThat(response)
                .isEqualTo(productResponse);
    }

    @Test
    void getByIdWhenNonExistingProduct_shouldPrepareFailView() throws ProductBusinessException {
        var product = ProductMother.random();
        var id = product.getId();
        var throwable = new ProductNotFoundException(String.format("The product with id %d does not exists!", id));
        given(gateway.existsById(id))
                .willReturn(false);
        given(presenter.prepareFailView(any(ProductBusinessException.class)))
                .willThrow(throwable);

        var response = catchThrowable(() -> {
            var productResponse = boundary.getById(id);
        });

        verify(gateway).existsById(id);
        verify(presenter).prepareFailView(any(ProductBusinessException.class));
        assertThat(response)
                .isEqualTo(throwable);
    }

    @Test
    void getAll_shouldPrepareSuccessfulView() throws ProductBusinessException {
        var product = ProductMother.random();
        var products = List.of(product);
        var productResponse = mapper.map(products);
        given(gateway.getAll())
                .willReturn(products);
        given(presenter.prepareSuccessfulView(productResponse))
                .willReturn(productResponse);

        var response = boundary.getAll();

        verify(gateway).getAll();
        verify(presenter).prepareSuccessfulView(productResponse);
        assertThat(response)
                .isEqualTo(productResponse);
    }


}