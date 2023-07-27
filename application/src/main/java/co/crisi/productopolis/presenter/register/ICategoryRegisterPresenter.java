package co.crisi.productopolis.presenter.register;

import co.crisi.productopolis.exception.CategoryBusinessException;
import co.crisi.productopolis.model.response.CategoryResponse;

public interface ICategoryRegisterPresenter {

    CategoryResponse prepareSuccessfulView(CategoryResponse response);

    CategoryResponse prepareFailView(CategoryBusinessException exception) throws CategoryBusinessException;


}
