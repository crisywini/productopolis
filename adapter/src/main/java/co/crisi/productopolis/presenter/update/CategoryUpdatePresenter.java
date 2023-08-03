package co.crisi.productopolis.presenter.update;

import co.crisi.productopolis.exception.CategoryBusinessException;
import co.crisi.productopolis.model.response.CategoryResponse;

public class CategoryUpdatePresenter implements ICategoryUpdatePresenter {

    @Override
    public CategoryResponse prepareSuccessfulView(CategoryResponse response) {
        return response;
    }

    @Override
    public CategoryResponse prepareFailView(CategoryBusinessException exception) throws CategoryBusinessException {
        throw exception;
    }

}
