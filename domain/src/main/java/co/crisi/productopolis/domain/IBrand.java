package co.crisi.productopolis.domain;

import java.util.List;

public interface IBrand {

    Long getId();

    String getName();

    String getDescription();

    List<IProduct> getProducts();


}
