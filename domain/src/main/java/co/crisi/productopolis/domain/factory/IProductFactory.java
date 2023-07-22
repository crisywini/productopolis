package co.crisi.productopolis.domain.factory;

import co.crisi.productopolis.domain.IAttribute;
import co.crisi.productopolis.domain.IBrand;
import co.crisi.productopolis.domain.ICategory;
import co.crisi.productopolis.domain.IImage;
import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.domain.IReview;
import java.time.LocalDate;
import java.util.List;

public interface IProductFactory {

    IProduct create(Long id, String name, String description, Double price, Long stock, Boolean isFeatured,
            Boolean isActive, IBrand brand, List<IAttribute> attributes, List<ICategory> categories,
            List<IReview> reviews, List<IImage> images);


    IProduct create(Long id, String name, String description, Double price, Long stock, Boolean isFeatured,
            Boolean isActive, IBrand brand, List<IAttribute> attributes, List<ICategory> categories);

}
