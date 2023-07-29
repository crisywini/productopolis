package co.crisi.productopolis.boundaries.input.extract;

import co.crisi.productopolis.exception.AttributeBusinessException;
import co.crisi.productopolis.model.response.AttributeResponse;

public interface IAttributeExtractBoundary extends
        IExtractBoundary<Long, AttributeResponse, AttributeBusinessException> {


}
