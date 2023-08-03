package co.crisi.productopolis.presenter.extract;

import co.crisi.productopolis.exception.CategoryBusinessException;
import co.crisi.productopolis.model.response.CategoryResponse;
import java.util.List;

public class CategoryExtractPresenter implements ICategoryExtractPresenter {

    @Override
    public CategoryResponse prepareSuccessfulView(CategoryResponse response) {
        return response;
    }

    @Override
    public CategoryResponse prepareFailView(CategoryBusinessException exception) throws CategoryBusinessException {
        throw exception;
    }

    @Override
    public List<CategoryResponse> prepareSuccessfulView(List<CategoryResponse> response) {
        return response;
    }

}
