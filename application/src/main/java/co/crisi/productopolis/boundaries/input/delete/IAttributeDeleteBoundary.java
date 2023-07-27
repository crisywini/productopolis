package co.crisi.productopolis.boundaries.input.delete;

import co.crisi.productopolis.exception.AttributeBusinessException;

public interface IAttributeDeleteBoundary {

    Long deleteById(Long id) throws AttributeBusinessException;

}
