package co.crisi.productopolis.domain.objectmother;

import co.crisi.productopolis.domain.ICategory;
import co.crisi.productopolis.domain.factory.impl.CategoryFactory;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMother {

    private final CategoryFactory FACTORY = new CategoryFactory();

    public ICategory random() {
        return FACTORY.create(2L, "Books", "The books of randomness");
    }
}
