package co.crisi.productopolis.presenter.register;

import co.crisi.productopolis.exception.BusinessException;
import co.crisi.productopolis.model.response.ProductResponse;

public class ProductRegisterPresenter implements IProductRegisterPresenter {

    @Override
    public ProductResponse prepareSuccessfulView(ProductResponse response) {
        return response;
    }

    @Override
    public ProductResponse prepareFailView(BusinessException exception) throws BusinessException {
        throw exception;
    }

}
