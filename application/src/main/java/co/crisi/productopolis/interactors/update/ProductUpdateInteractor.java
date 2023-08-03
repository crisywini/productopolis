package co.crisi.productopolis.interactors.update;

import co.crisi.productopolis.boundaries.input.update.IProductUpdateBoundary;
import co.crisi.productopolis.boundaries.output.IAttributeExtractGateway;
import co.crisi.productopolis.boundaries.output.IBrandExtractGateway;
import co.crisi.productopolis.boundaries.output.ICategoryExtractGateway;
import co.crisi.productopolis.boundaries.output.IProductExtractGateway;
import co.crisi.productopolis.boundaries.output.IProductUpdateGateway;
import co.crisi.productopolis.domain.Product;
import co.crisi.productopolis.exception.AttributeNotFoundException;
import co.crisi.productopolis.exception.BrandNotFoundException;
import co.crisi.productopolis.exception.BusinessException;
import co.crisi.productopolis.exception.CategoryNotFoundException;
import co.crisi.productopolis.exception.ProductBusinessException;
import co.crisi.productopolis.exception.ProductNotFoundException;
import co.crisi.productopolis.interactors.ProductValidatorInteractor;
import co.crisi.productopolis.model.request.ProductRequest;
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

    private final IBrandExtractGateway brandExtractGateway;

    private final IAttributeExtractGateway attributeExtractGateway;

    private final ICategoryExtractGateway categoryExtractGateway;

    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);


    @Override
    public ProductResponse update(ProductUpdateRequest request) throws BusinessException {
        return validateProductExistence(request)
                .flatMap(this::validateSameProductId)
                .flatMap(this::validateBrandExistence)
                .flatMap(this::validateAttributesExistence)
                .flatMap(this::validateCategoriesExistence)
                .flatMap(this::updateProduct)
                .fold(presenter::prepareFailView, presenter::prepareSuccessfulView);
    }

    private Either<BusinessException, ProductUpdateRequest> validateProductExistence(
            ProductUpdateRequest request) {
        return gateway.existsById(request.productId()) ? Either.right(request)
                : Either.left(new ProductNotFoundException(
                        String.format("The product with id %d was not found!", request.productId())));
    }

    public Either<BusinessException, ProductUpdateRequest> validateSameProductId(ProductUpdateRequest request) {
        if (Objects.nonNull(request.newInfo().id()) && request.productId().equals(request.newInfo().id())) {
            return Either.right(request);
        }
        return Either.left(new ProductBusinessException("The product ids are different!"));
    }

    public Either<BusinessException, ProductUpdateRequest> validateBrandExistence(ProductUpdateRequest request) {
        if (!brandExtractGateway.existsById(request.newInfo().brandId())) {
            return Either.left(new BrandNotFoundException(
                    String.format("The brand with id %d was not found!", request.newInfo().brandId())));
        }
        return Either.right(request);
    }

    public Either<BusinessException, ProductUpdateRequest> validateAttributesExistence(ProductUpdateRequest request) {
        var idsNotFound = request.newInfo().attributeIds().stream()
                .filter(id -> !attributeExtractGateway.existsById(id))
                .toList();

        if (!idsNotFound.isEmpty()) {
            return Either.left(
                    new AttributeNotFoundException("The ids " + idsNotFound + " were not found!"));
        }
        return Either.right(request);
    }

    public Either<BusinessException, ProductUpdateRequest> validateCategoriesExistence(ProductUpdateRequest request) {
        var idsNotFound = request.newInfo().categoryIds().stream()
                .filter(id -> !categoryExtractGateway.existsById(id))
                .toList();

        if (!idsNotFound.isEmpty()) {
            return Either.left(
                    new CategoryNotFoundException("The ids " + idsNotFound + " were not found!"));
        }
        return Either.right(request);
    }


    /**
     * TODO: add images and review when created
     */
    private Either<BusinessException, ProductResponse> updateProduct(ProductUpdateRequest request) {
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
