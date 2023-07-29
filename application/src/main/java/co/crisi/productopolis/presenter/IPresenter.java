package co.crisi.productopolis.presenter;

import co.crisi.productopolis.exception.BusinessException;

public interface IPresenter<R, E extends BusinessException> {

    R prepareSuccessfulView(R response);

    R prepareFailView(E exception) throws E;


}
