package co.crisi.productopolis.interactors.update;

import co.crisi.productopolis.boundaries.input.update.IProductUpdateBoundary;
import co.crisi.productopolis.boundaries.output.IProductExtractGateway;
import co.crisi.productopolis.boundaries.output.IProductUpdateGateway;
import co.crisi.productopolis.domain.Product;
import co.crisi.productopolis.exception.ProductBusinessException;
import co.crisi.productopolis.exception.ProductNotFoundException;
import co.crisi.productopolis.model.request.ProductUpdateRequest;
import co.crisi.productopolis.model.response.ProductResponse;
import co.crisi.productopolis.model.response.mapper.ProductMapper;
import co.crisi.productopolis.presenter.update.IProductUpdatePresenter;
import io.vavr.control.Either;
import io.vavr.control.Try;
import java.time.LocalDate;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;

@RequiredArgsConstructor
public class ProductUpdateInteractor implements IProductUpdateBoundary {

    private final IProductUpdateGateway gateway;

    private final IProductUpdatePresenter presenter;

    private final IProductExtractGateway productExtractGateway;

    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);


    @Override
    public ProductResponse update(ProductUpdateRequest request) throws ProductBusinessException {
        return validateProductExistence(request)
                .flatMap(this::validateSameProductId)
                .flatMap(this::updateProduct)
                .fold(presenter::prepareFailView, presenter::prepareSuccessfulView);
    }

    private Either<ProductBusinessException, ProductUpdateRequest> validateProductExistence(
            ProductUpdateRequest request) {
        return gateway.existsById(request.productId()) ? Either.right(request)
                : Either.left(new ProductNotFoundException(
                        String.format("The product with id %d was not found!", request.productId())));
    }

    private Either<ProductBusinessException, ProductUpdateRequest> validateSameProductId(ProductUpdateRequest request) {
        if (Objects.nonNull(request.newInfo().id()) && request.productId().equals(request.newInfo().id())) {
            return Either.right(request);
        }
        return Either.left(new ProductBusinessException("The product ids are different!"));
    }

    /**
     * TODO: add images and review when created
     */
    private Either<ProductBusinessException, ProductResponse> updateProduct(ProductUpdateRequest request) {
        var product = productExtractGateway.getById(request.productId());
        return Try.of(() -> {
                    var productUpdate = Product.builder()
                            .id(request.productId())
                            .name(request.newInfo().name())
                            .description(request.newInfo().description())
                            .price(request.newInfo().price())
                            .stock(request.newInfo().stock())
                            .isFeatured(request.newInfo().isFeatured())
                            .isActive(request.newInfo().isActive())
                            .brand(product.getBrand())
                            .attributes(product.getAttributes())
                            .categories(product.getCategories())
                            .creationDate(product.getCreationDate())
                            .lastUpdated(LocalDate.now())
                            .build();
                    return mapper.map(gateway.update(request.productId(), productUpdate));

                }).toEither()
                .mapLeft(throwable -> new ProductBusinessException(throwable.getMessage()));

    }

}
