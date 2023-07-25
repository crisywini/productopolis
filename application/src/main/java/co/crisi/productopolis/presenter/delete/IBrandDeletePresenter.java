package co.crisi.productopolis.presenter.delete;

import co.crisi.productopolis.exception.BrandBusinessException;

public interface IBrandDeletePresenter {

    Long prepareSuccessfulView(Long id);

    Long prepareFailView(BrandBusinessException exception) throws BrandBusinessException;

}
