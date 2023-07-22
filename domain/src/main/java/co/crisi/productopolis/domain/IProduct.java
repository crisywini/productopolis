package co.crisi.productopolis.domain;

import java.time.LocalDate;

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

}
