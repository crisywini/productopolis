package co.crisi.productopolis.domain;

import co.crisi.productopolis.domain.validator.decorator.NonNullValidatorDecorator;
import co.crisi.productopolis.domain.validator.decorator.NotEmptyValidatorDecorator;
import co.crisi.productopolis.domain.validator.impl.ValidatorImpl;

public record Attribute(Long id, String name, String description, String value) implements IAttribute {

    public Attribute(Long id, String name, String description, String value) {
        var validator = new ValidatorImpl();
        var nonNullDecorator = new NonNullValidatorDecorator(validator);
        var notEmptyDecorator = new NotEmptyValidatorDecorator(nonNullDecorator);
        this.id = nonNullDecorator.validate(id, "id");
        this.name = notEmptyDecorator.validate(name, "name");
        this.description = notEmptyDecorator.validate(description, "description");
        this.value = notEmptyDecorator.validate(value, "value");
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getValue() {
        return value;
    }

}
