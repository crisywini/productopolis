package co.crisi.productopolis.interactors.update;


import co.crisi.productopolis.boundaries.input.update.IProductUpdateBoundary;
import co.crisi.productopolis.boundaries.output.IAttributeExtractGateway;
import co.crisi.productopolis.boundaries.output.IBrandExtractGateway;
import co.crisi.productopolis.boundaries.output.ICategoryExtractGateway;
import co.crisi.productopolis.boundaries.output.IProductExtractGateway;
import co.crisi.productopolis.boundaries.output.IProductUpdateGateway;
import co.crisi.productopolis.domain.Product;
import co.crisi.productopolis.domain.ProductRequestMother;
import co.crisi.productopolis.domain.objectmother.ProductMother;
import co.crisi.productopolis.exception.AttributeNotFoundException;
import co.crisi.productopolis.exception.BrandNotFoundException;
import co.crisi.productopolis.exception.BusinessException;
import co.crisi.productopolis.exception.CategoryNotFoundException;
import co.crisi.productopolis.exception.ProductBusinessException;
import co.crisi.productopolis.exception.ProductNotFoundException;
import co.crisi.productopolis.model.request.ProductUpdateRequest;
import co.crisi.productopolis.model.response.mapper.ProductMapper;
import co.crisi.productopolis.presenter.update.IProductUpdatePresenter;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ProductUpdateInteractorTest {

    @Mock
    private IProductUpdateGateway gateway;

    @Mock
    private IProductUpdatePresenter presenter;

    @Mock
    private IProductExtractGateway productExtractGateway;

    @Mock
    private IBrandExtractGateway brandExtractGateway;

    @Mock
    private IAttributeExtractGateway attributeExtractGateway;

    @Mock
    private ICategoryExtractGateway categoryExtractGateway;

    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    private IProductUpdateBoundary boundary;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        boundary = new ProductUpdateInteractor(gateway, presenter, productExtractGateway, brandExtractGateway,
                attributeExtractGateway, categoryExtractGateway);
    }

    @Test
    void updateWhenAllValidationsOk_shouldPrepareSuccessfulView() {
        var newInfo = ProductRequestMother.random();
        var product = ProductMother.random();
        var id = 1L;
        var productToUpdate = Product.builder()
                .id(id)
                .name(newInfo.name())
                .description(newInfo.description())
                .price(newInfo.price())
                .stock(newInfo.stock())
                .isFeatured(newInfo.isFeatured())
                .isActive(newInfo.isActive())
                .brand(product.getBrand())
                .attributes(product.getAttributes())
                .categories(product.getCategories())
                .creationDate(product.getCreationDate())
                .lastUpdated(LocalDate.now())
                .build();
        var productResponseExpected = mapper.map(productToUpdate);
        var productUpdateRequest = new ProductUpdateRequest(id, newInfo);
        given(gateway.existsById(id))
                .willReturn(true);
        given(brandExtractGateway.existsById(anyLong()))
                .willReturn(true);
        given(attributeExtractGateway.existsById(anyLong()))
                .willReturn(true);
        given(categoryExtractGateway.existsById(anyLong()))
                .willReturn(true);
        given(productExtractGateway.getById(id))
                .willReturn(product);
        given(gateway.update(id, productToUpdate))
                .willReturn(productToUpdate);
        given(presenter.prepareSuccessfulView(productResponseExpected))
                .willReturn(productResponseExpected);

        var response = boundary.update(productUpdateRequest);

        verify(gateway).existsById(id);
        verify(brandExtractGateway).existsById(anyLong());
        verify(attributeExtractGateway, times(2)).existsById(anyLong());
        verify(categoryExtractGateway, times(2)).existsById(anyLong());
        verify(productExtractGateway).getById(id);
        verify(gateway).update(id, productToUpdate);
        verify(presenter).prepareSuccessfulView(productResponseExpected);
        assertThat(response)
                .isNotNull()
                .isEqualTo(productResponseExpected);

    }

    @Test
    void updateWhenNonExistingProduct_shouldPrepareFailView() {
        var newInfo = ProductRequestMother.random();
        var id = 1L;
        var productUpdateRequest = new ProductUpdateRequest(id, newInfo);
        var throwable = new ProductNotFoundException(
                String.format("The product with id %d was not found!", id));
        given(gateway.existsById(id))
                .willReturn(false);
        given(presenter.prepareFailView(any(BusinessException.class)))
                .willThrow(throwable);

        var response = catchThrowable(() -> {
            var pr = boundary.update(productUpdateRequest);
        });

        verify(gateway).existsById(id);
        verify(presenter).prepareFailView(any(BusinessException.class));
        assertThat(response)
                .isNotNull()
                .isEqualTo(throwable);

    }

    @Test
    void updateWhenNoSameProduct_shouldPrepareFailView() {
        var newInfo = ProductRequestMother.random();
        var id = 2L;
        var productUpdateRequest = new ProductUpdateRequest(id, newInfo);
        var throwable = new ProductBusinessException("The product ids are different!");

        given(gateway.existsById(id))
                .willReturn(true);
        given(presenter.prepareFailView(any(BusinessException.class)))
                .willThrow(throwable);

        var response = catchThrowable(() -> {
            var pr = boundary.update(productUpdateRequest);
        });

        verify(gateway).existsById(id);
        verify(presenter).prepareFailView(any(BusinessException.class));
        assertThat(response)
                .isNotNull()
                .isEqualTo(throwable);

    }

    @Test
    void updateWhenNonExistingBrand_shouldPrepareFailView() {
        var newInfo = ProductRequestMother.random();
        var id = 1L;
        var productUpdateRequest = new ProductUpdateRequest(id, newInfo);
        var throwable = new BrandNotFoundException(
                String.format("The brand with id %d was not found!", productUpdateRequest.newInfo().brandId()));
        given(gateway.existsById(id))
                .willReturn(true);
        given(brandExtractGateway.existsById(productUpdateRequest.newInfo().brandId()))
                .willReturn(false);
        given(presenter.prepareFailView(any(BusinessException.class)))
                .willThrow(throwable);

        var response = catchThrowable(() -> {
            var pr = boundary.update(productUpdateRequest);
        });

        verify(gateway).existsById(id);
        verify(brandExtractGateway).existsById(productUpdateRequest.newInfo().brandId());
        verify(presenter).prepareFailView(any(BusinessException.class));
        assertThat(response)
                .isNotNull()
                .isEqualTo(throwable);

    }

    @Test
    void updateWhenNonExistingAttributes_shouldPrepareFailView() {
        var newInfo = ProductRequestMother.random();
        var id = 1L;
        var productUpdateRequest = new ProductUpdateRequest(id, newInfo);
        var attributeIds = productUpdateRequest.newInfo().attributeIds();
        var throwable = new AttributeNotFoundException("The ids " + attributeIds + " were not found!");
        given(gateway.existsById(id))
                .willReturn(true);
        given(brandExtractGateway.existsById(productUpdateRequest.newInfo().brandId()))
                .willReturn(true);
        given(attributeExtractGateway.existsById(anyLong()))
                .willReturn(false);
        given(presenter.prepareFailView(any(BusinessException.class)))
                .willThrow(throwable);

        var response = catchThrowable(() -> {
            var pr = boundary.update(productUpdateRequest);
        });

        verify(gateway).existsById(id);
        verify(brandExtractGateway).existsById(productUpdateRequest.newInfo().brandId());
        verify(attributeExtractGateway, times(2)).existsById(anyLong());
        verify(presenter).prepareFailView(any(BusinessException.class));
        assertThat(response)
                .isNotNull()
                .isEqualTo(throwable);

    }

    @Test
    void updateWhenNonExistingCategories_shouldPrepareFailView() {
        var newInfo = ProductRequestMother.random();
        var id = 1L;
        var productUpdateRequest = new ProductUpdateRequest(id, newInfo);
        var categoryIds = productUpdateRequest.newInfo().categoryIds();
        var throwable = new CategoryNotFoundException("The ids " + categoryIds + " were not found!");
        given(gateway.existsById(id))
                .willReturn(true);
        given(brandExtractGateway.existsById(productUpdateRequest.newInfo().brandId()))
                .willReturn(true);
        given(attributeExtractGateway.existsById(anyLong()))
                .willReturn(true);
        given(categoryExtractGateway.existsById(anyLong()))
                .willReturn(false);
        given(presenter.prepareFailView(any(BusinessException.class)))
                .willThrow(throwable);

        var response = catchThrowable(() -> {
            var pr = boundary.update(productUpdateRequest);
        });

        verify(gateway).existsById(id);
        verify(brandExtractGateway).existsById(productUpdateRequest.newInfo().brandId());
        verify(attributeExtractGateway, times(2)).existsById(anyLong());
        verify(categoryExtractGateway, times(2)).existsById(anyLong());
        verify(presenter).prepareFailView(any(BusinessException.class));
        assertThat(response)
                .isNotNull()
                .isEqualTo(throwable);

    }
    
}