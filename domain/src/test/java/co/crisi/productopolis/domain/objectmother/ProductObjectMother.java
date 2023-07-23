package co.crisi.productopolis.domain.objectmother;

import co.crisi.productopolis.domain.Brand;
import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.domain.factory.IBrandFactory;
import co.crisi.productopolis.domain.factory.IProductFactory;
import co.crisi.productopolis.domain.factory.impl.BrandFactory;
import co.crisi.productopolis.domain.factory.impl.ProductFactory;
import java.util.ArrayList;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductObjectMother {

    private final IProductFactory factory = new ProductFactory();

    private final IBrandFactory brandFactory = new BrandFactory();

    public IProduct random() {
        return factory.create(1L, "The name of the Wind", "The best book of all times",
                70000.0, 12L, true, true, brandFactory.create(1L, "Book store", "great one"),
                new ArrayList<>(), new ArrayList<>());
    }

}
