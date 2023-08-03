package co.crisi.productopolis.presenter.extract;

import co.crisi.productopolis.exception.BrandBusinessException;
import co.crisi.productopolis.model.response.BrandResponse;
import java.util.List;

public class BrandExtractPresenter implements IBrandExtractPresenter {

    @Override
    public BrandResponse prepareSuccessfulView(BrandResponse response) {
        return response;
    }

    @Override
    public BrandResponse prepareFailView(BrandBusinessException exception) throws BrandBusinessException {
        throw exception;
    }

    @Override
    public List<BrandResponse> prepareSuccessfulView(List<BrandResponse> response) {
        return response;
    }

}
