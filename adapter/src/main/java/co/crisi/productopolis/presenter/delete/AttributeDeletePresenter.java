package co.crisi.productopolis.presenter.delete;

import co.crisi.productopolis.exception.AttributeBusinessException;

public class AttributeDeletePresenter implements IAttributeDeletePresenter {

    @Override
    public Long prepareSuccessfulView(Long response) {
        return response;
    }

    @Override
    public Long prepareFailView(AttributeBusinessException exception) throws AttributeBusinessException {
        throw exception;
    }

}
