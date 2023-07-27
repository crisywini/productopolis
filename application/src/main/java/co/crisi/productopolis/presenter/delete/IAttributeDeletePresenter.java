package co.crisi.productopolis.presenter.delete;

import co.crisi.productopolis.exception.AttributeBusinessException;

public interface IAttributeDeletePresenter {

    Long prepareSuccessfulView(Long id);

    Long prepareFailView(AttributeBusinessException exception) throws AttributeBusinessException;

}
