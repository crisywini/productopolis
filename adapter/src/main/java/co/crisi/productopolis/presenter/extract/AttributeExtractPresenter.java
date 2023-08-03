package co.crisi.productopolis.presenter.extract;

import co.crisi.productopolis.exception.AttributeBusinessException;
import co.crisi.productopolis.model.response.AttributeResponse;
import java.util.List;

public class AttributeExtractPresenter implements IAttributeExtractPresenter {

    @Override
    public AttributeResponse prepareSuccessfulView(AttributeResponse response) {
        return response;
    }

    @Override
    public AttributeResponse prepareFailView(AttributeBusinessException exception) throws AttributeBusinessException {
        throw exception;
    }

    @Override
    public List<AttributeResponse> prepareSuccessfulView(List<AttributeResponse> response) {
        return response;
    }

}
