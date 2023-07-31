package co.crisi.productopolis.boundaries.input.update;

import co.crisi.productopolis.exception.BrandBusinessException;
import co.crisi.productopolis.model.request.BrandUpdateRequest;
import co.crisi.productopolis.model.response.BrandResponse;

public interface IBrandUpdateBoundary extends
        IUpdateBoundary<BrandResponse, BrandUpdateRequest, BrandBusinessException> {

    BrandResponse update(BrandUpdateRequest request) throws BrandBusinessException;

}
