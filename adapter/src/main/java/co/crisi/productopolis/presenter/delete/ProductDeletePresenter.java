package co.crisi.productopolis.presenter.delete;

import co.crisi.productopolis.exception.ProductBusinessException;

public class ProductDeletePresenter implements IProductDeletePresenter {

    @Override
    public Long prepareSuccessfulView(Long response) {
        return response;
    }

    @Override
    public Long prepareFailView(ProductBusinessException exception) throws ProductBusinessException {
        throw exception;
    }

}
