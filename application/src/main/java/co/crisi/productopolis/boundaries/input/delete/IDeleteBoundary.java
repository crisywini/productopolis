package co.crisi.productopolis.boundaries.input.delete;

import co.crisi.productopolis.exception.BusinessException;

public interface IDeleteBoundary<ID, E extends BusinessException> {

    ID deleteById(ID id) throws E;

}
