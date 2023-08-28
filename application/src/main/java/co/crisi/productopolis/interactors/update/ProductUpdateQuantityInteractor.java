package co.crisi.productopolis.interactors.update;

import co.crisi.productopolis.boundaries.input.update.IProductUpdateQuantityBoundary;
import co.crisi.productopolis.boundaries.output.IProductExtractGateway;
import co.crisi.productopolis.boundaries.output.IProductUpdateGateway;
import co.crisi.productopolis.domain.Product;
import co.crisi.productopolis.exception.BusinessException;
import co.crisi.productopolis.exception.ProductBusinessException;
import co.crisi.productopolis.exception.ProductNotFoundException;
import co.crisi.productopolis.model.request.ProductUpdateQuantityRequest;
import co.crisi.productopolis.model.request.ProductUpdateRequest;
import co.crisi.productopolis.model.response.ProductResponse;
import co.crisi.productopolis.model.response.mapper.ProductMapper;
import co.crisi.productopolis.presenter.update.IProductUpdatePresenter;
import io.vavr.control.Either;
import io.vavr.control.Try;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;

@RequiredArgsConstructor
public class ProductUpdateQuantityInteractor implements IProductUpdateQuantityBoundary {

    private final IProductUpdatePresenter presenter;

    private final IProductUpdateGateway gateway;

    private final IProductExtractGateway productExtractGateway;

    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);


    @Override
    public ProductResponse updateQuantity(ProductUpdateQuantityRequest request) {
        return validateProductExistence(request)
                .flatMap(this::validateQuantity)
                .flatMap(this::updateProduct)
                .fold(presenter::prepareFailView, presenter::prepareSuccessfulView);
    }

    private Either<BusinessException, ProductUpdateQuantityRequest> validateQuantity(
            ProductUpdateQuantityRequest request) {
        if (request.quantity() < 0) {
            return Either.left(new ProductBusinessException("The quantity is negative!"));
        }
        var product = productExtractGateway.getById(request.productId());
        if (product.getStock() < request.quantity()) {
            return Either.left(new ProductBusinessException("The quantity is more than the stock now!"));
        }
        return Either.right(request);
    }

    private Either<BusinessException, ProductResponse> updateProduct(ProductUpdateQuantityRequest request) {
        var product = productExtractGateway.getById(request.productId());
        return Try.of(() -> {
                    var productUpdate = Product.builder()
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
                    return mapper.map(gateway.update(request.productId(), productUpdate));

                }).toEither()
                .mapLeft(throwable -> new ProductBusinessException(throwable.getMessage()));

    }

    private Either<BusinessException, ProductUpdateQuantityRequest> validateProductExistence(
            ProductUpdateQuantityRequest request) {
        return gateway.existsById(request.productId()) ? Either.right(request)
                : Either.left(new ProductNotFoundException(
                        String.format("The product with id %d was not found!", request.productId())));
    }

}
