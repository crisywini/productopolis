package co.crisi.productopolis.presenter;

import co.crisi.productopolis.exception.ProductBusinessException;
import co.crisi.productopolis.model.response.register.ProductResponse;

public interface IProductPresenter {

    ProductResponse prepareSuccessfulView(ProductResponse response);

    ProductResponse prepareFailView(ProductBusinessException exception) throws ProductBusinessException;

}
