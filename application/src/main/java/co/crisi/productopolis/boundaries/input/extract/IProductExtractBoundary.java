package co.crisi.productopolis.boundaries.input.extract;

import co.crisi.productopolis.exception.ProductBusinessException;
import co.crisi.productopolis.model.response.ProductResponse;

public interface IProductExtractBoundary extends IExtractBoundary<Long, ProductResponse, ProductBusinessException> {
}
