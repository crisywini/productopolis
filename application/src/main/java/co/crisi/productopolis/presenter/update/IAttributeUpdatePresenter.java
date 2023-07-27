package co.crisi.productopolis.presenter.update;

import co.crisi.productopolis.exception.AttributeBusinessException;
import co.crisi.productopolis.model.response.AttributeResponse;

public interface IAttributeUpdatePresenter {

    AttributeResponse prepareSuccessfulView(AttributeResponse response);

    AttributeResponse prepareFailView(AttributeBusinessException exception) throws AttributeBusinessException;

}
