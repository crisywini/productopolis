package co.crisi.productopolis.boundaries.output.register;

import co.crisi.productopolis.domain.IBrand;

public interface IBrandRegisterGateway {

    void save(IBrand brand);

    boolean existsById(Long id);

}
