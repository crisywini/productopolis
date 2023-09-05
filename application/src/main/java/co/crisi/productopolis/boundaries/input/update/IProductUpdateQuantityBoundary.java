package co.crisi.productopolis.boundaries.input.update;

import co.crisi.productopolis.model.request.ProductUpdateQuantityRequest;
import co.crisi.productopolis.model.response.ProductResponse;

public interface IProductUpdateQuantityBoundary {

    ProductResponse updateQuantity(ProductUpdateQuantityRequest request);

}
