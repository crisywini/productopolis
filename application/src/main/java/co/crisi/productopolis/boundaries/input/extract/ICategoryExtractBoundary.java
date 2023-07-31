package co.crisi.productopolis.boundaries.input.extract;

import co.crisi.productopolis.exception.CategoryBusinessException;
import co.crisi.productopolis.model.response.CategoryResponse;

public interface ICategoryExtractBoundary extends IExtractBoundary<Long, CategoryResponse, CategoryBusinessException> {

}
