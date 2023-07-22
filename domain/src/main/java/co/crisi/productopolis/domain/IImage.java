package co.crisi.productopolis.domain;

public interface IImage {

    Long getId();

    String getName();

    String getUrl();

    IProduct getProduct();

}
