package co.crisi.productopolis.presenter.delete;

import co.crisi.productopolis.exception.CategoryBusinessException;

public class CategoryDeletePresenter implements ICategoryDeletePresenter {

    @Override
    public Long prepareSuccessfulView(Long response) {
        return response;
    }

    @Override
    public Long prepareFailView(CategoryBusinessException exception) throws CategoryBusinessException {
        throw exception;
    }

}
