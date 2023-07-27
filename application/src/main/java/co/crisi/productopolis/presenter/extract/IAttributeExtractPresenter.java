package co.crisi.productopolis.presenter.extract;

import co.crisi.productopolis.exception.AttributeBusinessException;
import co.crisi.productopolis.model.response.AttributeResponse;
import java.util.List;

public interface IAttributeExtractPresenter {

    AttributeResponse prepareSuccessfulView(AttributeResponse response);

    AttributeResponse prepareFailView(AttributeBusinessException exception) throws AttributeBusinessException;

    List<AttributeResponse> prepareSuccessfulView(List<AttributeResponse> response);

}
