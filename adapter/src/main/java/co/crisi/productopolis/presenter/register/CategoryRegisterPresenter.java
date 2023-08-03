package co.crisi.productopolis.presenter.register;

import co.crisi.productopolis.exception.CategoryBusinessException;
import co.crisi.productopolis.model.response.CategoryResponse;

public class CategoryRegisterPresenter implements ICategoryRegisterPresenter {

    @Override
    public CategoryResponse prepareSuccessfulView(CategoryResponse response) {
        return response;
    }

    @Override
    public CategoryResponse prepareFailView(CategoryBusinessException exception) throws CategoryBusinessException {
        throw exception;
    }

}
