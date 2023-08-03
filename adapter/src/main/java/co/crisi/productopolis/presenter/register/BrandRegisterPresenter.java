package co.crisi.productopolis.presenter.register;

import co.crisi.productopolis.exception.BrandBusinessException;
import co.crisi.productopolis.model.response.BrandResponse;

public class BrandRegisterPresenter implements IBrandRegisterPresenter {

    @Override
    public BrandResponse prepareSuccessfulView(BrandResponse response) {
        return response;
    }

    @Override
    public BrandResponse prepareFailView(BrandBusinessException exception) throws BrandBusinessException {
        throw exception;
    }

}
