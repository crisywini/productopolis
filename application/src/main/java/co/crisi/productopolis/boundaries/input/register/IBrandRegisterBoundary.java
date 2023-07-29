package co.crisi.productopolis.boundaries.input.register;

import co.crisi.productopolis.exception.BrandBusinessException;
import co.crisi.productopolis.model.request.BrandRequest;
import co.crisi.productopolis.model.response.BrandResponse;

public interface IBrandRegisterBoundary extends IRegisterBoundary<BrandResponse, BrandRequest, BrandBusinessException>{


}
