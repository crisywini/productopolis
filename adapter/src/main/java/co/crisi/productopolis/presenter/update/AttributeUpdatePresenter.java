package co.crisi.productopolis.presenter.update;

import co.crisi.productopolis.exception.AttributeBusinessException;
import co.crisi.productopolis.model.response.AttributeResponse;

public class AttributeUpdatePresenter implements IAttributeUpdatePresenter {

    @Override
    public AttributeResponse prepareSuccessfulView(AttributeResponse response) {
        return response;
    }

    @Override
    public AttributeResponse prepareFailView(AttributeBusinessException exception) throws AttributeBusinessException {
        throw exception;
    }

}
