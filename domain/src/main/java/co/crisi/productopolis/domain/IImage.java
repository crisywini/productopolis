package co.crisi.productopolis.domain;

public interface IImage {

    Long getId();

    Long getName();

    String getUrl();

    IProduct getProduct();

}
