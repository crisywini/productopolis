package co.crisi.productopolis.boundaries.output;

import co.crisi.productopolis.domain.IBrand;

public interface IBrandUpdateGateway extends IBrandGateway {

    IBrand update(Long id, IBrand newInfo);

}
