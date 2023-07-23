package co.crisi.productopolis.domain.factory.impl;

import co.crisi.productopolis.domain.Brand;
import co.crisi.productopolis.domain.IBrand;
import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.domain.factory.IBrandFactory;
import java.util.ArrayList;
import java.util.List;

public class BrandFactory implements IBrandFactory {

    @Override
    public IBrand create(Long id, String name, String description, List<IProduct> products) {
        return new Brand(id, name, description, products);
    }

    @Override
    public IBrand create(Long id, String name, String description) {
        return new Brand(id, name, description, new ArrayList<>());
    }

}
