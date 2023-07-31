package co.crisi.productopolis.interactors.register;

import co.crisi.productopolis.boundaries.input.register.IProductRegisterBoundary;
import co.crisi.productopolis.boundaries.output.IAttributeExtractGateway;
import co.crisi.productopolis.boundaries.output.IBrandExtractGateway;
import co.crisi.productopolis.boundaries.output.ICategoryExtractGateway;
import co.crisi.productopolis.boundaries.output.IProductRegisterGateway;
import co.crisi.productopolis.domain.factory.IProductFactory;
import co.crisi.productopolis.exception.AttributeNotFoundException;
import co.crisi.productopolis.exception.BrandNotFoundException;
import co.crisi.productopolis.exception.BusinessException;
import co.crisi.productopolis.exception.CategoryNotFoundException;
import co.crisi.productopolis.exception.RepeatedProductException;
import co.crisi.productopolis.model.request.ProductRequest;
import co.crisi.productopolis.model.response.ProductResponse;
import co.crisi.productopolis.model.response.mapper.ProductMapper;
import co.crisi.productopolis.presenter.register.IProductRegisterPresenter;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;

@RequiredArgsConstructor
public class ProductRegisterInteractor implements IProductRegisterBoundary {

    private final IProductFactory factory;

    private final IProductRegisterGateway gateway;

    private final IProductRegisterPresenter presenter;

    private final IBrandExtractGateway brandExtractGateway;

    private final IAttributeExtractGateway attributeExtractGateway;

    private final ICategoryExtractGateway categoryExtractGateway;

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);


    @Override
    public ProductResponse create(ProductRequest request) throws BusinessException {

        if (gateway.existsById(request.id())) {
            return presenter.prepareFailView(
                    new RepeatedProductException(String.format("Product with id %d already exists!", request.id())));
        }
        if (!brandExtractGateway.existsById(request.brandId())) {
            return presenter.prepareFailView(new BrandNotFoundException(
                    String.format("The brand with id %d was not found!", request.brandId())));
        }

        var attIds = request.attributeIds().stream()
                .allMatch(attributeExtractGateway::existsById);

        if (!attIds) {
            var idsNotFound = request.attributeIds().stream()
                    .filter(id -> !attributeExtractGateway.existsById(id)).toList();
            return presenter.prepareFailView(
                    new AttributeNotFoundException("The ids " + idsNotFound + " were not found!"));
        }
        var catIds = request.categoryIds().stream()
                .allMatch(categoryExtractGateway::existsById);

        if (!catIds) {
            var idsNotFound = request.categoryIds().stream()
                    .filter(id -> !categoryExtractGateway.existsById(id)).toList();
            return presenter.prepareFailView(
                    new CategoryNotFoundException("The ids " + idsNotFound + " were not found!"));
        }
        var brand = brandExtractGateway.getById(request.brandId());

        var attributes = request.attributeIds().stream()
                .map(attributeExtractGateway::getById)
                .toList();
        var categories = request.categoryIds().stream()
                .map(categoryExtractGateway::getById)
                .toList();
        try {
            var product = factory.create(request.id(), request.name(), request.description(), request.price(),
                    request.stock(),
                    request.isFeatured(), request.isActive(), brand, attributes, categories);
            var productSaved = gateway.save(product);
            return presenter.prepareSuccessfulView(productMapper.map(productSaved));
        } catch (RuntimeException e) {
            return presenter.prepareFailView(new BusinessException(e.getMessage()));
        }


    }

}
