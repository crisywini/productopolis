package co.crisi.productopolis.domain.objectmother;

import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.domain.factory.IProductFactory;
import co.crisi.productopolis.domain.factory.impl.ProductFactory;
import java.util.ArrayList;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductMother {

    private final IProductFactory factory = new ProductFactory();

    public IProduct random() {
        return factory.create(1L, "The name of the Wind", "The best book of all times",
                70000.0, 12L, true, true, BrandMother.random(),
                new ArrayList<>(), new ArrayList<>());
    }

}
