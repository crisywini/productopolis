package co.crisi.productopolis.interactors.register;

import co.crisi.productopolis.boundaries.input.register.IProductRegisterBoundary;
import co.crisi.productopolis.boundaries.output.IAttributeExtractGateway;
import co.crisi.productopolis.boundaries.output.IBrandExtractGateway;
import co.crisi.productopolis.boundaries.output.ICategoryExtractGateway;
import co.crisi.productopolis.boundaries.output.IProductRegisterGateway;
import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.domain.ProductRequestMother;
import co.crisi.productopolis.domain.factory.IProductFactory;
import co.crisi.productopolis.domain.factory.impl.ProductFactory;
import co.crisi.productopolis.domain.objectmother.AttributeMother;
import co.crisi.productopolis.domain.objectmother.BrandMother;
import co.crisi.productopolis.domain.objectmother.CategoryMother;
import co.crisi.productopolis.domain.objectmother.ProductMother;
import co.crisi.productopolis.exception.*;
import co.crisi.productopolis.model.request.ProductRequest;
import co.crisi.productopolis.model.response.ProductResponse;
import co.crisi.productopolis.model.response.mapper.ProductMapper;
import co.crisi.productopolis.presenter.register.IProductRegisterPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class ProductRegisterInteractorTest {

    private final IProductFactory factory = new ProductFactory();

    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Mock
    private IProductRegisterGateway gateway;

    @Mock
    private IProductRegisterPresenter presenter;

    private IProductRegisterBoundary boundary;

    @Mock
    private IBrandExtractGateway brandExtractGateway;

    @Mock
    private IAttributeExtractGateway attributeExtractGateway;

    @Mock
    private ICategoryExtractGateway categoryExtractGateway;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        boundary = new ProductRegisterInteractor(factory, gateway, presenter, brandExtractGateway, attributeExtractGateway, categoryExtractGateway);
    }

    @Test
    void createWhenNonExistingProduct_shouldPrepareSuccessfulView() throws BusinessException {
        var productRequest = ProductRequestMother.random();
        var product = ProductMother.random();
        var response = mapper.map(product);
        givenExistenceForHappyPath(productRequest);
        var brand = BrandMother.random();
        given(brandExtractGateway.getById(brand.getId()))
                .willReturn(brand);
        var attribute = AttributeMother.random();
        given(attributeExtractGateway.getById(anyLong()))
                .willReturn(attribute);
        var category = CategoryMother.random();
        given(categoryExtractGateway.getById(anyLong()))
                .willReturn(category);
        given(presenter.prepareSuccessfulView(any(ProductResponse.class)))
                .willReturn(response);

        var productResponse = boundary.create(productRequest);

        verify(gateway).existsById(productRequest.id());
        verify(brandExtractGateway).existsById(productRequest.brandId());
        verify(attributeExtractGateway, times(2)).existsById(anyLong());
        verify(categoryExtractGateway, times(2)).existsById(anyLong());
        verify(presenter).prepareSuccessfulView(response);
        assertThat(productResponse)
                .isNotNull()
                .isEqualTo(response);
    }

    private void givenExistenceForHappyPath(ProductRequest request){
        given(gateway.existsById(request.id()))
                .willReturn(false);
        given(brandExtractGateway.existsById(request.brandId()))
                .willReturn(true);
        given(attributeExtractGateway.existsById(anyLong()))
                .willReturn(true);
        given(categoryExtractGateway.existsById(anyLong()))
                .willReturn(true);
    }

    @Test
    void createWhenExistingProduct_shouldPrepareFailView() throws BusinessException {
        var request = ProductRequestMother.random();
        var throwable = new RepeatedProductException(String
                .format("Product with id %d already exists!", request.id()));
        given(gateway.existsById(request.id()))
                .willReturn(true);
        given(presenter.prepareFailView(any(BusinessException.class)))
                .willThrow(throwable);
        var response = catchThrowable(() -> {
            var productResponse = boundary.create(request);
        });
        verify(gateway).existsById(request.id());
        verify(presenter).prepareFailView(any(BusinessException.class));
        assertThat(response)
                .isEqualTo(throwable);
    }
    @Test
    void createWhenExistingProductAndNoExistingBrand_shouldPrepareFailView() throws BusinessException {
        var request = ProductRequestMother.random();
        var throwable = new BrandNotFoundException(
                String.format("The brand with id %d was not found!", request.brandId()));
        given(gateway.existsById(request.id()))
                .willReturn(false);
        given(brandExtractGateway.existsById(request.brandId()))
                .willReturn(false);
        given(presenter.prepareFailView(any(BusinessException.class)))
                .willThrow(throwable);
        var response = catchThrowable(() -> {
            var productResponse = boundary.create(request);
        });
        verify(gateway).existsById(request.id());
        verify(brandExtractGateway).existsById(request.brandId());
        verify(presenter).prepareFailView(any(BusinessException.class));
        assertThat(response)
                .isEqualTo(throwable);
    }

    @Test
    void createWhenExistingProductAndNoExistingAttribute_shouldPrepareFailView() throws BusinessException {
        var request = ProductRequestMother.random();
        var idsNotFound  = request.attributeIds();
        var throwable = new AttributeNotFoundException("The ids " + idsNotFound + " were not found!");
        given(gateway.existsById(request.id()))
                .willReturn(false);
        given(brandExtractGateway.existsById(request.brandId()))
                .willReturn(true);
        given(attributeExtractGateway.existsById(anyLong()))
                .willReturn(false);
        given(presenter.prepareFailView(any(BusinessException.class)))
                .willThrow(throwable);
        var response = catchThrowable(() -> {
            var productResponse = boundary.create(request);
        });
        verify(gateway).existsById(request.id());
        verify(brandExtractGateway).existsById(request.brandId());
        verify(attributeExtractGateway, times(2)).existsById(anyLong());
        verify(presenter).prepareFailView(any(BusinessException.class));
        assertThat(response)
                .isEqualTo(throwable);
    }

    @Test
    void createWhenExistingProductAndNoExistingCategory_shouldPrepareFailView() throws BusinessException {
        var request = ProductRequestMother.random();
        var idsNotFound  = request.categoryIds();
        var throwable = new CategoryNotFoundException("The ids " + idsNotFound + " were not found!");
        given(gateway.existsById(request.id()))
                .willReturn(false);
        given(brandExtractGateway.existsById(request.brandId()))
                .willReturn(true);
        given(attributeExtractGateway.existsById(anyLong()))
                .willReturn(true);
        given(categoryExtractGateway.existsById(anyLong()))
                .willReturn(false);
        given(presenter.prepareFailView(any(BusinessException.class)))
                .willThrow(throwable);
        var response = catchThrowable(() -> {
            var productResponse = boundary.create(request);
        });
        verify(gateway).existsById(request.id());
        verify(brandExtractGateway).existsById(request.brandId());
        verify(attributeExtractGateway, times(2)).existsById(anyLong());
        verify(categoryExtractGateway, times(2)).existsById(anyLong());
        verify(presenter).prepareFailView(any(BusinessException.class));
        assertThat(response)
                .isEqualTo(throwable);
    }

    @Test
    void createWhenExistingProductAndNoExistingAttributeAndCategory_shouldPrepareFailView() throws BusinessException {
        var request = ProductRequestMother.random();
        var idsNotFound  = request.attributeIds();
        var throwable = new AttributeNotFoundException("The ids " + idsNotFound + " were not found!");
        given(gateway.existsById(request.id()))
                .willReturn(false);
        given(brandExtractGateway.existsById(request.brandId()))
                .willReturn(true);
        given(attributeExtractGateway.existsById(anyLong()))
                .willReturn(false);
        given(categoryExtractGateway.existsById(anyLong()))
                .willReturn(false);
        given(presenter.prepareFailView(any(BusinessException.class)))
                .willThrow(throwable);
        var response = catchThrowable(() -> {
            var productResponse = boundary.create(request);
        });
        verify(gateway).existsById(request.id());
        verify(brandExtractGateway).existsById(request.brandId());
        verify(attributeExtractGateway, times(2)).existsById(anyLong());
        verifyNoInteractions(categoryExtractGateway);
        verify(presenter).prepareFailView(any(BusinessException.class));
        assertThat(response)
                .isEqualTo(throwable);
    }

}