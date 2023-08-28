package co.crisi.productopolis.domain;

import co.crisi.productopolis.domain.validator.decorator.NonNullValidatorDecorator;
import co.crisi.productopolis.domain.validator.decorator.NotEmptyValidatorDecorator;
import co.crisi.productopolis.domain.validator.impl.ValidatorImpl;
import java.util.List;
import lombok.Builder;

public record Brand(Long id, String name, String description, List<IProduct> products)
        implements IBrand {

    @Builder
    public Brand(Long id, String name, String description, List<IProduct> products) {
        var validator = new ValidatorImpl();
        var nonNullDecorator = new NonNullValidatorDecorator(validator);
        var notEmptyDecorator = new NotEmptyValidatorDecorator(nonNullDecorator);
        this.id = id;
        this.name = notEmptyDecorator.validate(name, "name");
        this.description = notEmptyDecorator.validate(description, "description");
        this.products = products;
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
    public List<IProduct> getProducts() {
        return products;
    }

}
