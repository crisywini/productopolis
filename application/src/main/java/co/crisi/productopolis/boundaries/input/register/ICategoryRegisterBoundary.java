package co.crisi.productopolis.boundaries.input.register;

import co.crisi.productopolis.exception.CategoryBusinessException;
import co.crisi.productopolis.model.request.CategoryRequest;
import co.crisi.productopolis.model.response.CategoryResponse;

public interface ICategoryRegisterBoundary extends IRegisterBoundary<CategoryResponse, CategoryRequest, CategoryBusinessException> {


}
