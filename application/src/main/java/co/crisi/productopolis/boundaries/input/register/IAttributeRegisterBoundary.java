package co.crisi.productopolis.boundaries.input.register;

import co.crisi.productopolis.exception.AttributeBusinessException;
import co.crisi.productopolis.model.request.AttributeRequest;
import co.crisi.productopolis.model.response.AttributeResponse;

public interface IAttributeRegisterBoundary {

    AttributeResponse create(AttributeRequest request) throws AttributeBusinessException;

}
