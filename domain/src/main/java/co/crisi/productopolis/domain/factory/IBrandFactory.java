package co.crisi.productopolis.domain.factory;

import co.crisi.productopolis.domain.IBrand;
import co.crisi.productopolis.domain.IProduct;
import java.util.List;

public interface IBrandFactory {

    IBrand create(Long id, String name, String description, List<IProduct> products);

    IBrand create(Long id, String name, String description);

}
