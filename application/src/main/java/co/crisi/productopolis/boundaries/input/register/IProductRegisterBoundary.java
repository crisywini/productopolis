package co.crisi.productopolis.boundaries.input.register;

import co.crisi.productopolis.exception.BusinessException;
import co.crisi.productopolis.model.request.ProductRequest;
import co.crisi.productopolis.model.response.ProductResponse;

public interface IProductRegisterBoundary extends
        IRegisterBoundary<ProductResponse, ProductRequest, BusinessException> {

}
