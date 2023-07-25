package co.crisi.productopolis.interactors.register;

import co.crisi.productopolis.boundaries.input.register.IBrandRegisterBoundary;
import co.crisi.productopolis.boundaries.output.IBrandRegisterGateway;
import co.crisi.productopolis.domain.factory.IBrandFactory;
import co.crisi.productopolis.exception.BrandBusinessException;
import co.crisi.productopolis.exception.RepeatedBrandException;
import co.crisi.productopolis.model.request.BrandRequest;
import co.crisi.productopolis.model.response.BrandResponse;
import co.crisi.productopolis.model.response.mapper.BrandMapper;
import co.crisi.productopolis.presenter.register.IBrandRegisterPresenter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrandRegisterInteractor implements IBrandRegisterBoundary {

    private final IBrandRegisterPresenter presenter;

    private final IBrandFactory factory;

    private final IBrandRegisterGateway gateway;

    private final BrandMapper mapper;

    @Override
    public BrandResponse create(BrandRequest request) throws BrandBusinessException {

        if (gateway.existsById(request.id())) {
            return presenter.prepareFailView(
                    new RepeatedBrandException(String.format("The brand with id %d already exists!", request.id())));
        }

        var brand = factory.create(request.id(), request.name(), request.description());

        gateway.save(brand);

        return presenter.prepareSuccessfulView(mapper.map(brand));
    }

}
