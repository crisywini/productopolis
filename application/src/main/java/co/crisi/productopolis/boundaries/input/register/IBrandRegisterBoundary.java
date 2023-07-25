package co.crisi.productopolis.boundaries.input.register;

import co.crisi.productopolis.exception.BrandBusinessException;
import co.crisi.productopolis.model.request.register.BrandRequest;
import co.crisi.productopolis.model.response.register.BrandResponse;

public interface IBrandRegisterBoundary {

    BrandResponse create(BrandRequest request) throws BrandBusinessException;

}
