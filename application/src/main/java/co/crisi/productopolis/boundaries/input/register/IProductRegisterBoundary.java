package co.crisi.productopolis.boundaries.input.register;

import co.crisi.productopolis.exception.ProductBusinessException;
import co.crisi.productopolis.model.request.register.ProductRequest;
import co.crisi.productopolis.model.response.ProductResponse;

public interface IProductRegisterBoundary {


    ProductResponse create(ProductRequest request) throws ProductBusinessException;

}
