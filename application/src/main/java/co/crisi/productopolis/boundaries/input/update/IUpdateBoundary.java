package co.crisi.productopolis.boundaries.input.update;

import co.crisi.productopolis.exception.BusinessException;

public interface IUpdateBoundary<R, RQ, E extends BusinessException> {

    R update(RQ request) throws E;

}
