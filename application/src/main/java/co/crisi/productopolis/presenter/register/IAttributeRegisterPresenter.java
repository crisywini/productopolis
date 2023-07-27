package co.crisi.productopolis.presenter.register;

import co.crisi.productopolis.exception.AttributeBusinessException;
import co.crisi.productopolis.model.response.AttributeResponse;

public interface IAttributeRegisterPresenter {

    AttributeResponse prepareSuccessfulView(AttributeResponse response);

    AttributeResponse prepareFailView(AttributeBusinessException exception) throws AttributeBusinessException;

}
