package co.crisi.productopolis.boundaries.input.update;

import co.crisi.productopolis.exception.CategoryBusinessException;
import co.crisi.productopolis.model.request.CategoryUpdateRequest;
import co.crisi.productopolis.model.response.CategoryResponse;

public interface ICategoryUpdateBoundary extends
        IUpdateBoundary<CategoryResponse, CategoryUpdateRequest, CategoryBusinessException> {

}
