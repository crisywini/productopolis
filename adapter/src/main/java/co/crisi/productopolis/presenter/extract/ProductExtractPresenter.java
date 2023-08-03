package co.crisi.productopolis.presenter.extract;

import co.crisi.productopolis.exception.ProductBusinessException;
import co.crisi.productopolis.model.response.ProductResponse;
import java.util.List;

public class ProductExtractPresenter implements IProductExtractPresenter {

    @Override
    public ProductResponse prepareSuccessfulView(ProductResponse response) {
        return response;
    }

    @Override
    public ProductResponse prepareFailView(ProductBusinessException exception) throws ProductBusinessException {
        throw exception;
    }

    @Override
    public List<ProductResponse> prepareSuccessfulView(List<ProductResponse> response) {
        return response;
    }

}
