package co.crisi.productopolis.boundaries.input.delete;

import co.crisi.productopolis.exception.BrandBusinessException;

public interface IBrandDeleteBoundary {

    Long deleteById(Long id) throws BrandBusinessException;

}
