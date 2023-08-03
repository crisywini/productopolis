package co.crisi.productopolis.interactors.experimental.validator;

import co.crisi.productopolis.annotations.ExperimentalFeature;
import co.crisi.productopolis.boundaries.output.IAttributeExtractGateway;
import co.crisi.productopolis.boundaries.output.IBrandExtractGateway;
import co.crisi.productopolis.boundaries.output.ICategoryExtractGateway;
import co.crisi.productopolis.boundaries.output.IProductRegisterGateway;
import co.crisi.productopolis.exception.AttributeNotFoundException;
import co.crisi.productopolis.exception.BrandNotFoundException;
import co.crisi.productopolis.exception.BusinessException;
import co.crisi.productopolis.exception.CategoryNotFoundException;
import co.crisi.productopolis.exception.ProductBusinessException;
import co.crisi.productopolis.exception.RepeatedProductException;
import co.crisi.productopolis.model.request.ProductRequest;
import co.crisi.productopolis.model.request.ProductUpdateRequest;
import io.vavr.control.Either;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

@ExperimentalFeature
@RequiredArgsConstructor
public class ProductValidatorInteractor {

    private final IProductRegisterGateway gateway;

    private final IBrandExtractGateway brandExtractGateway;

    private final IAttributeExtractGateway attributeExtractGateway;

    private final ICategoryExtractGateway categoryExtractGateway;


    public Either<BusinessException, ProductRequest> validateProductExistence(ProductRequest request) {
        if (gateway.existsById(request.id())) {
            return Either.left(new RepeatedProductException(String
                    .format("Product with id %d already exists!", request.id())));
        }
        return Either.right(request);
    }

    public Either<BusinessException, ProductRequest> validateBrandExistence(ProductRequest request) {
        if (!brandExtractGateway.existsById(request.brandId())) {
            return Either.left(new BrandNotFoundException(
                    String.format("The brand with id %d was not found!", request.brandId())));
        }
        return Either.right(request);
    }

    public Either<BusinessException, ProductRequest> validateAttributesExistence(ProductRequest request) {
        var idsNotFound = request.attributeIds().stream()
                .filter(id -> !attributeExtractGateway.existsById(id))
                .toList();

        if (!idsNotFound.isEmpty()) {
            return Either.left(
                    new AttributeNotFoundException("The ids " + idsNotFound + " were not found!"));
        }
        return Either.right(request);
    }

    public Either<BusinessException, ProductRequest> validateCategoriesExistence(ProductRequest request) {
        var idsNotFound = request.categoryIds().stream()
                .filter(id -> !categoryExtractGateway.existsById(id))
                .toList();

        if (!idsNotFound.isEmpty()) {
            return Either.left(
                    new CategoryNotFoundException("The ids " + idsNotFound + " were not found!"));
        }
        return Either.right(request);
    }

    public Either<ProductBusinessException, ProductUpdateRequest> validateSameProductId(ProductUpdateRequest request) {
        if (Objects.nonNull(request.newInfo().id()) && request.productId().equals(request.newInfo().id())) {
            return Either.right(request);
        }
        return Either.left(new ProductBusinessException("The product ids are different!"));
    }

}
