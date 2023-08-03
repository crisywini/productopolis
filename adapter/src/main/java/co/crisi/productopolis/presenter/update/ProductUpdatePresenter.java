package co.crisi.productopolis.presenter.update;

import co.crisi.productopolis.exception.BusinessException;
import co.crisi.productopolis.model.response.ProductResponse;

public class ProductUpdatePresenter implements IProductUpdatePresenter{

    @Override
    public ProductResponse prepareSuccessfulView(ProductResponse response) {
        return response;
    }

    @Override
    public ProductResponse prepareFailView(BusinessException exception) throws BusinessException {
        throw exception;
    }

}
