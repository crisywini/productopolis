package co.crisi.productopolis.boundaries.output;

import co.crisi.productopolis.domain.IBrand;
import java.util.List;

public interface IBrandExtractGateway extends IBrandGateway{

    List<IBrand> getAll();

    IBrand getById(Long id);

}
