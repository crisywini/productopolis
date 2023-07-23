package co.crisi.productopolis.domain.factory;

import co.crisi.productopolis.domain.IAttribute;

public interface IAttributeFactory {

    IAttribute create(Long id, String name, String description);

}
