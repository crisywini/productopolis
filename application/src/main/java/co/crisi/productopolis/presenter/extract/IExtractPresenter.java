package co.crisi.productopolis.presenter.extract;

import co.crisi.productopolis.exception.BusinessException;
import co.crisi.productopolis.presenter.IPresenter;
import java.util.List;

public interface IExtractPresenter<R, E extends BusinessException> extends IPresenter<R, E> {

    List<R> prepareSuccessfulView(List<R> response);

}
