package co.crisi.productopolis.domain;

import java.time.LocalDate;

public interface IReview {

    Long getId();

    Integer getRating();

    String getMessage();

    IProduct getProduct();

    Long getUserId();

    LocalDate getCreationDate();

}
