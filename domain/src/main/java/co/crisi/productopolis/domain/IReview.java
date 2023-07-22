package co.crisi.productopolis.domain;

public interface IReview {

    Long getId();

    Integer getRating();

    String getMessage();

    IProduct getProduct();

    Long getUserId();

}
