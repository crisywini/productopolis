package co.crisi.productopolis.domain;

import java.time.LocalDate;
import java.util.List;

public interface IProduct {


    Long getId();

    String getName();

    String getDescription();

    Double getPrice();

    Long getStock();

    LocalDate getCreationDate();

    LocalDate getLastUpdated();

    Boolean isFeatured();

    Boolean isActive();

    IBrand getBrand();

    List<IAttribute> getAttributes();

    List<ICategory> getCategories();

    List<IReview> getReviews();

    List<IImage> getImages();

}
