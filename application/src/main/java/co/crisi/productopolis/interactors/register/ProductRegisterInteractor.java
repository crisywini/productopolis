package co.crisi.productopolis.interactors.register;

import co.crisi.productopolis.boundaries.input.extract.IBrandExtractBoundary;
import co.crisi.productopolis.boundaries.input.register.IProductRegisterBoundary;
import co.crisi.productopolis.boundaries.output.IProductRegisterGateway;
import co.crisi.productopolis.domain.factory.IProductFactory;
import co.crisi.productopolis.exception.ProductBusinessException;
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

    private final IBrandExtractBoundary brandExtractBoundary;

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);


    @Override
    public ProductResponse create(ProductRequest request) throws ProductBusinessException {
        if (gateway.existsById(request.id())) {
            return presenter.prepareFailView(
                    new RepeatedProductException(String.format("Product with id %d already exists!", request.id())));
        }
        return null;
    }

}
