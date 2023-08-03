package co.crisi.productopolis.boundaries.input.update;

import co.crisi.productopolis.exception.ProductBusinessException;
import co.crisi.productopolis.model.request.ProductUpdateRequest;
import co.crisi.productopolis.model.response.ProductResponse;

public interface IProductUpdateBoundary extends
        IUpdateBoundary<ProductResponse, ProductUpdateRequest, ProductBusinessException> {

}
