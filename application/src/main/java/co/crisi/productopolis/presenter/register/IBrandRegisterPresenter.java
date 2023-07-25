package co.crisi.productopolis.presenter.register;


import co.crisi.productopolis.exception.BrandBusinessException;
import co.crisi.productopolis.model.response.BrandResponse;

public interface IBrandRegisterPresenter {

    BrandResponse prepareSuccessfulView(BrandResponse response);

    BrandResponse prepareFailView(BrandBusinessException exception) throws BrandBusinessException;

}
