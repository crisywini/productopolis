package co.crisi.productopolis.presenter.delete;

import co.crisi.productopolis.exception.BrandBusinessException;

public class BrandDeletePresenter implements IBrandDeletePresenter {

    @Override
    public Long prepareSuccessfulView(Long response) {
        return response;
    }

    @Override
    public Long prepareFailView(BrandBusinessException exception) throws BrandBusinessException {
        throw exception;
    }

}
