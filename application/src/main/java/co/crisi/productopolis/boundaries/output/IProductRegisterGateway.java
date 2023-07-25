package co.crisi.productopolis.boundaries.output;

import co.crisi.productopolis.domain.IProduct;

public interface IProductRegisterGateway {

    void save(IProduct product);

    boolean existsById(Long id);

}