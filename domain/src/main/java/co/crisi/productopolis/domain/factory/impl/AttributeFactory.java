package co.crisi.productopolis.domain.factory.impl;

import co.crisi.productopolis.domain.Attribute;
import co.crisi.productopolis.domain.IAttribute;
import co.crisi.productopolis.domain.factory.IAttributeFactory;

public class AttributeFactory implements IAttributeFactory {

    @Override
    public IAttribute create(Long id, String name, String description, String value) {
        return new Attribute(id, name, description, value);
    }

}
