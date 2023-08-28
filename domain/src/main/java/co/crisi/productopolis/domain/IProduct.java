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

    List<? extends IAttribute> getAttributes();

    List<? extends ICategory> getCategories();

    List<? extends IReview> getReviews();

    List<? extends IImage> getImages();

}
