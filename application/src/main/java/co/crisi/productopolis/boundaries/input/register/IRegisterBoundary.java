package co.crisi.productopolis.boundaries.input.register;

import co.crisi.productopolis.exception.BusinessException;

public interface IRegisterBoundary<T, U, E extends BusinessException> {

    T create(U request) throws E;

}
