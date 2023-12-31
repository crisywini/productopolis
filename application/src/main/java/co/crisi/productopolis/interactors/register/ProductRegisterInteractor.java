package co.crisi.productopolis.interactors.register;

import co.crisi.productopolis.boundaries.input.register.IProductRegisterBoundary;
import co.crisi.productopolis.boundaries.output.IAttributeExtractGateway;
import co.crisi.productopolis.boundaries.output.IBrandExtractGateway;
import co.crisi.productopolis.boundaries.output.ICategoryExtractGateway;
import co.crisi.productopolis.boundaries.output.IProductRegisterGateway;
import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.domain.Product;
import co.crisi.productopolis.exception.AttributeNotFoundException;
import co.crisi.productopolis.exception.BrandNotFoundException;
import co.crisi.productopolis.exception.BusinessException;
import co.crisi.productopolis.exception.CategoryNotFoundException;
import co.crisi.productopolis.exception.RepeatedProductException;
import co.crisi.productopolis.model.request.ProductRequest;
import co.crisi.productopolis.model.response.ProductResponse;
import co.crisi.productopolis.model.response.mapper.ProductMapper;
import co.crisi.productopolis.presenter.register.IProductRegisterPresenter;
import io.vavr.control.Either;
import io.vavr.control.Try;
import java.time.LocalDate;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;


@RequiredArgsConstructor
public class ProductRegisterInteractor implements IProductRegisterBoundary {

    private final IProductRegisterGateway gateway;

    private final IProductRegisterPresenter presenter;

    private final IBrandExtractGateway brandExtractGateway;

    private final IAttributeExtractGateway attributeExtractGateway;

    private final ICategoryExtractGateway categoryExtractGateway;

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);


    @Override
    public ProductResponse create(ProductRequest request) throws BusinessException {
        return validateProductExistence(request)
                .flatMap(this::validateBrandExistence)
                .flatMap(this::validateAttributesExistence)
                .flatMap(this::validateCategoriesExistence)
                .flatMap(this::createProduct)
                .map(productMapper::map)
                .fold(presenter::prepareFailView, presenter::prepareSuccessfulView);
    }

    private Either<BusinessException, ProductRequest> validateProductExistence(ProductRequest request) {
        if (Objects.nonNull(request.id()) && gateway.existsById(request.id())) {
            return Either.left(new RepeatedProductException(String
                    .format("Product with id %d already exists!", request.id())));
        }
        return Either.right(request);
    }

    private Either<BusinessException, ProductRequest> validateBrandExistence(ProductRequest request) {
        if (!brandExtractGateway.existsById(request.brandId())) {
            return Either.left(new BrandNotFoundException(
                    String.format("The brand with id %d was not found!", request.brandId())));
        }
        return Either.right(request);
    }

    private Either<BusinessException, ProductRequest> validateAttributesExistence(ProductRequest request) {
        var idsNotFound = request.attributeIds().stream()
                .filter(id -> !attributeExtractGateway.existsById(id))
                .toList();

        if (!idsNotFound.isEmpty()) {
            return Either.left(
                    new AttributeNotFoundException("The ids " + idsNotFound + " were not found!"));
        }
        return Either.right(request);
    }

    private Either<BusinessException, ProductRequest> validateCategoriesExistence(ProductRequest request) {
        var idsNotFound = request.categoryIds().stream()
                .filter(id -> !categoryExtractGateway.existsById(id))
                .toList();

        if (!idsNotFound.isEmpty()) {
            return Either.left(
                    new CategoryNotFoundException("The ids " + idsNotFound + " were not found!"));
        }
        return Either.right(request);
    }

    private Either<BusinessException, ? extends IProduct> createProduct(ProductRequest request) {
        var brand = brandExtractGateway.getById(request.brandId());

        var attributes = request.attributeIds().stream()
                .map(attributeExtractGateway::getById)
                .toList();
        var categories = request.categoryIds().stream()
                .map(categoryExtractGateway::getById)
                .toList();
        return Try.of(() -> {
                    var product = Product.builder()
                            .id(request.id())
                            .name(request.name())
                            .description(request.description())
                            .price(request.price())
                            .stock(request.stock())
                            .isFeatured(request.isFeatured())
                            .isActive(request.isActive())
                            .brand(brand)
                            .attributes(attributes)
                            .categories(categories)
                            .creationDate(LocalDate.now())
                            .lastUpdated(LocalDate.now())
                            .build();
                    return gateway.save(product);
                })
                .toEither()
                .mapLeft(throwable -> new BusinessException(throwable.getMessage()));
    }


}
