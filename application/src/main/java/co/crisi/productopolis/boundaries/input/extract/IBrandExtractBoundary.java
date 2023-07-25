package co.crisi.productopolis.boundaries.input.extract;

import co.crisi.productopolis.exception.BrandBusinessException;
import co.crisi.productopolis.model.response.BrandResponse;
import java.util.List;

public interface IBrandExtractBoundary {

    BrandResponse getById(Long id) throws BrandBusinessException;

    List<BrandResponse> getAll() throws BrandBusinessException;

}
