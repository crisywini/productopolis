package co.crisi.productopolis.domain.objectmother;

import co.crisi.productopolis.domain.IBrand;
import co.crisi.productopolis.domain.factory.impl.BrandFactory;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BrandMother {
    private final BrandFactory factory = new BrandFactory();

    public IBrand random(){
        return factory.create(1L, "Book store", "great one");
    }
}
