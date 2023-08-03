package co.crisi.productopolis.presenter.update;

import co.crisi.productopolis.exception.BrandBusinessException;
import co.crisi.productopolis.model.response.BrandResponse;

public class BrandUpdatePresenter implements IBrandUpdatePresenter {

    @Override
    public BrandResponse prepareSuccessfulView(BrandResponse response) {
        return response;
    }

    @Override
    public BrandResponse prepareFailView(BrandBusinessException exception) throws BrandBusinessException {
        throw exception;
    }

}
