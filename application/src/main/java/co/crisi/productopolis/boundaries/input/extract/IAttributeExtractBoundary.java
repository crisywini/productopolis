package co.crisi.productopolis.boundaries.input.extract;

import co.crisi.productopolis.exception.AttributeBusinessException;
import co.crisi.productopolis.model.response.AttributeResponse;
import java.util.List;

public interface IAttributeExtractBoundary {

    AttributeResponse getById(Long id) throws AttributeBusinessException;

    List<AttributeResponse> getAll();

}
