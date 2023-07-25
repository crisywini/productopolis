package co.crisi.productopolis.interactors.register;

import co.crisi.productopolis.boundaries.input.extract.IBrandExtractBoundary;
import co.crisi.productopolis.boundaries.input.register.IProductRegisterBoundary;
import co.crisi.productopolis.boundaries.output.IProductRegisterGateway;
import co.crisi.productopolis.domain.factory.IProductFactory;
import co.crisi.productopolis.exception.ProductBusinessException;
import co.crisi.productopolis.exception.RepeatedProductException;
import co.crisi.productopolis.model.request.register.ProductRequest;
import co.crisi.productopolis.model.response.ProductResponse;
import co.crisi.productopolis.model.response.mapper.ProductMapper;
import co.crisi.productopolis.presenter.register.IProductPresenter;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;

@RequiredArgsConstructor
public class ProductInteractor implements IProductRegisterBoundary {

    private final IProductFactory factory;

    private final IProductRegisterGateway gateway;

    private final IProductPresenter presenter;

    private final IBrandExtractBoundary brandExtractBoundary;

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);


    @Override
    public ProductResponse create(ProductRequest request) throws ProductBusinessException {
        if (gateway.existsById(request.id())) {
            return presenter.prepareFailView(
                    new RepeatedProductException(String.format("Product with id %d already exists!", request.id())));
        }
/*
        try {
            var brand  = brandExtractBoundary.getById(request.id());
            IProduct product = factory.create(request.id(), request.name(), request.description(), request.price(),
                    request.stock(), request.isFeatured(), request.isActive(),
                    request.brandId(),
                    request.attributeIds(),
                    request.categoryIds());

            gateway.save(product);
            var response = productMapper.map(product);
            return presenter.prepareSuccessfulView(response);
        } catch (NullFieldException | EmptyStringException |
                IllegalDateException | NegativeNumberException e) {
            return presenter.prepareFailView(new ProductBusinessException(e.getMessage()));
        }catch(BrandBusinessException e){
            return presenter.prepareFailView(new ProductBusinessException(e.getMessage()));
        }*/
        return null;
    }

}
