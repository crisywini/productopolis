package co.crisi.productopolis.interactors.update;

import co.crisi.productopolis.boundaries.input.update.IBrandUpdateBoundary;
import co.crisi.productopolis.boundaries.output.IBrandUpdateGateway;
import co.crisi.productopolis.domain.factory.IBrandFactory;
import co.crisi.productopolis.exception.BrandBusinessException;
import co.crisi.productopolis.exception.BrandNotFoundException;
import co.crisi.productopolis.model.request.BrandUpdateRequest;
import co.crisi.productopolis.model.response.BrandResponse;
import co.crisi.productopolis.model.response.mapper.BrandMapper;
import co.crisi.productopolis.presenter.update.IBrandUpdatePresenter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrandUpdateInteractor implements IBrandUpdateBoundary {

    private final IBrandFactory factory;

    private final IBrandUpdateGateway gateway;

    private final IBrandUpdatePresenter presenter;

    private final BrandMapper mapper;

    @Override
    public BrandResponse update(BrandUpdateRequest request) throws BrandBusinessException {

        if (!gateway.existsById(request.brandId())) {
            return presenter.prepareFailView(new BrandNotFoundException(
                    String.format("The brand with id %d does not exists!", request.brandId())));
        }

        var newInfo = request.newBrandInfo();
        var brandUpdate = factory.create(request.brandId(), newInfo.name(), newInfo.description());

        var updated = gateway.update(newInfo.id(), brandUpdate);

        return presenter.prepareSuccessfulView(mapper.map(updated));
    }

}
