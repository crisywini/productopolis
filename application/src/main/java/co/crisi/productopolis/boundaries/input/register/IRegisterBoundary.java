package co.crisi.productopolis.boundaries.input.register;

import co.crisi.productopolis.exception.BusinessException;

public interface IRegisterBoundary<R, RQ, E extends BusinessException> {

    R create(RQ request) throws E;

}
