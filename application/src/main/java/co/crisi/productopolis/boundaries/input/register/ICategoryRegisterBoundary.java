package co.crisi.productopolis.boundaries.input.register;

import co.crisi.productopolis.exception.CategoryBusinessException;
import co.crisi.productopolis.model.request.CategoryCreationRequest;
import co.crisi.productopolis.model.response.CategoryResponse;

public interface ICategoryRegisterBoundary {

    CategoryResponse create(CategoryCreationRequest request) throws CategoryBusinessException;

}
