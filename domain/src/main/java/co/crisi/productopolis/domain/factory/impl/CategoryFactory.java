package co.crisi.productopolis.domain.factory.impl;

import co.crisi.productopolis.domain.Category;
import co.crisi.productopolis.domain.ICategory;
import co.crisi.productopolis.domain.factory.ICategoryFactory;
import java.time.LocalDate;

public class CategoryFactory implements ICategoryFactory {

    @Override
    public ICategory create(Long id, String name, String description) {
        return new Category(id, name, description, LocalDate.now(), LocalDate.now());
    }

    @Override
    public ICategory create(Long id, String name, String description, LocalDate lastUpdated) {
        return new Category(id, name, description, LocalDate.now(), lastUpdated);
    }

}
