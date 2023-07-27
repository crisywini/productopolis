package co.crisi.productopolis.boundaries.output;

import co.crisi.productopolis.domain.IBrand;

public interface IBrandRegisterGateway extends IBrandGateway {

    void save(IBrand brand);


}
