package co.crisi.productopolis.domain.objectmother;

import co.crisi.productopolis.domain.IAttribute;
import co.crisi.productopolis.domain.factory.impl.AttributeFactory;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AttributeMother {

    private final AttributeFactory factory = new AttributeFactory();

    public IAttribute random(){
        return factory.create(1L, "Weight", "The weight of the product", "23L");
    }
}
