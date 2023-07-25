package co.crisi.productopolis.presenter;


import co.crisi.productopolis.exception.BrandBusinessException;
import co.crisi.productopolis.model.response.register.BrandResponse;

public interface IBrandPresenter {

    BrandResponse prepareSuccessfulView(BrandResponse response);

    BrandResponse prepareFailView(BrandBusinessException exception) throws BrandBusinessException;

}
