package co.crisi.productopolis.boundaries.input.extract;

import co.crisi.productopolis.exception.BrandBusinessException;
import co.crisi.productopolis.model.response.extract.BrandResponse;

public interface IBrandExtractBoundary {

    BrandResponse getById(Long id) throws BrandBusinessException;

}
