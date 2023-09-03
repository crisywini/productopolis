package co.crisi.productopolis.interactors.update;

import co.crisi.productopolis.boundaries.output.IProductExtractGateway;
import co.crisi.productopolis.boundaries.output.IProductUpdateGateway;
import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.domain.Product;
import co.crisi.productopolis.domain.ProductUpdateQuantityRequestMother;
import co.crisi.productopolis.domain.objectmother.ProductMother;
import co.crisi.productopolis.model.request.ProductUpdateQuantityRequest;
import co.crisi.productopolis.model.response.ProductResponse;
import co.crisi.productopolis.model.response.mapper.ProductMapper;
import co.crisi.productopolis.presenter.update.IProductUpdatePresenter;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ProductUpdateQuantityInteractorTest {


    @Mock
    private IProductUpdatePresenter presenter;

    @Mock
    private IProductUpdateGateway gateway;

    @Mock
    private IProductExtractGateway productExtractGateway;

    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @InjectMocks
    private ProductUpdateQuantityInteractor interactor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateQuantity_whenHappyPath_shouldUpdateQuantity() {
        var request = ProductUpdateQuantityRequestMother.random();
        var product = ProductMother.randomWithAttributesAndCategories();
        var productNewInfo = getProductUpdateForHappyPath(request, product);
        var expectedResponse = mapper.map(productNewInfo);
        given(gateway.existsById(request.productId()))
                .willReturn(true);
        given(productExtractGateway.getById(request.productId()))
                .willReturn(product);
        given(gateway.update(request.productId(), productNewInfo))
                .willReturn(productNewInfo);
        given(presenter.prepareSuccessfulView(any(ProductResponse.class)))
                .willReturn(expectedResponse);

        var response = interactor.updateQuantity(request);

        verify(gateway).existsById(request.productId());
        verify(productExtractGateway, times(2))
                .getById(request.productId());
        verify(gateway).update(request.productId(), productNewInfo);
        assertThat(response)
                .isNotNull()
                .extracting(ProductResponse::id, ProductResponse::name, ProductResponse::isActive)
                .contains(expectedResponse.id(), expectedResponse.name(), expectedResponse.isActive());
    }

    private Product getProductUpdateForHappyPath(ProductUpdateQuantityRequest request, IProduct product) {
        return Product.builder()
                .id(request.productId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock() - request.quantity())
                .isFeatured(product.isFeatured())
                .isActive(product.isActive())
                .brand(product.getBrand())
                .attributes(product.getAttributes())
                .categories(product.getCategories())
                .creationDate(product.getCreationDate())
                .lastUpdated(LocalDate.now())
                .build();
    }

}