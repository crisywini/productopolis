package co.crisi.productopolis.interactors.extract;

import co.crisi.productopolis.boundaries.input.extract.IBrandExtractBoundary;
import co.crisi.productopolis.boundaries.output.IBrandExtractGateway;
import co.crisi.productopolis.exception.BrandBusinessException;
import co.crisi.productopolis.exception.BrandNotFoundException;
import co.crisi.productopolis.model.response.BrandResponse;
import co.crisi.productopolis.model.response.mapper.BrandMapper;
import co.crisi.productopolis.presenter.extract.IBrandPresenter;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrandInteractor implements IBrandExtractBoundary {

    private final BrandMapper mapper;

    private final IBrandPresenter presenter;

    private final IBrandExtractGateway gateway;

    @Override
    public BrandResponse getById(Long id) throws BrandBusinessException {

        if (!gateway.existsById(id)) {
            return presenter.prepareFailView(
                    new BrandNotFoundException(String.format("The brand with id %d was not found!", id)));
        }

        var brand = gateway.getById(id);

        return presenter.prepareSuccessfulView(mapper.map(brand));
    }

    @Override
    public List<BrandResponse> getAll() {
        return presenter.prepareSuccessfulView(mapper.map(gateway.getAll()));
    }

}
