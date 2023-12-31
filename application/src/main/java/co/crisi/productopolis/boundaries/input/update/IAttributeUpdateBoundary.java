package co.crisi.productopolis.boundaries.input.update;

import co.crisi.productopolis.exception.AttributeBusinessException;
import co.crisi.productopolis.model.request.AttributeUpdateRequest;
import co.crisi.productopolis.model.response.AttributeResponse;

public interface IAttributeUpdateBoundary extends
        IUpdateBoundary<AttributeResponse, AttributeUpdateRequest, AttributeBusinessException> {


}
