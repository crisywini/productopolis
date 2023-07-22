package co.crisi.productopolis.domain.factory.impl;

import co.crisi.productopolis.domain.IAttribute;
import co.crisi.productopolis.domain.IBrand;
import co.crisi.productopolis.domain.ICategory;
import co.crisi.productopolis.domain.IImage;
import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.domain.IReview;
import co.crisi.productopolis.domain.Product;
import co.crisi.productopolis.domain.factory.IProductFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductFactory implements IProductFactory {

    @Override
    public IProduct create(Long id, String name, String description, Double price, Long stock, Boolean isFeatured,
            Boolean isActive, IBrand brand, List<IAttribute> attributes,
            List<ICategory> categories, List<IReview> reviews, List<IImage> images) {
        return new Product(id, name, description, price, stock,
                LocalDate.now(), LocalDate.now(), isFeatured, isActive,
                brand, attributes, categories,
                reviews, images);
    }

    @Override
    public IProduct create(Long id, String name, String description, Double price, Long stock, Boolean isFeatured,
            Boolean isActive, IBrand brand, List<IAttribute> attributes, List<ICategory> categories) {
        return new Product(id, name, description, price, stock,
                LocalDate.now(), LocalDate.now(), isFeatured, isActive,
                brand, attributes, categories,
                new ArrayList<>(), new ArrayList<>());
    }

}
