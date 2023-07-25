package co.crisi.productopolis.presenter.update;

import co.crisi.productopolis.exception.BrandBusinessException;
import co.crisi.productopolis.model.response.BrandResponse;

public interface IBrandUpdatePresenter {

    BrandResponse prepareSuccessfulView(BrandResponse response);

    BrandResponse prepareFailView(BrandBusinessException exception) throws BrandBusinessException;

}
