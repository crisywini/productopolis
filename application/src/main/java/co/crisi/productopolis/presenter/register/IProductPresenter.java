package co.crisi.productopolis.presenter.register;

import co.crisi.productopolis.exception.ProductBusinessException;
import co.crisi.productopolis.model.response.ProductResponse;

public interface IProductPresenter {

    ProductResponse prepareSuccessfulView(ProductResponse response);

    ProductResponse prepareFailView(ProductBusinessException exception) throws ProductBusinessException;

}
