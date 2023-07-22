package co.crisi.productopolis.domain;

import co.crisi.productopolis.domain.validator.decorator.NonNullValidatorDecorator;
import co.crisi.productopolis.domain.validator.decorator.NotEmptyValidatorDecorator;
import co.crisi.productopolis.domain.validator.decorator.ValidatorImpl;

public record Image(Long id, String name, String url, IProduct product) implements IImage {


    public Image(Long id, String name, String url, IProduct product) {
        var validator = new ValidatorImpl();
        var nonNullDecorator = new NonNullValidatorDecorator(validator);
        var notEmptyDecorator = new NotEmptyValidatorDecorator(nonNullDecorator);
        this.id = nonNullDecorator.validate(id, "id");
        this.name = notEmptyDecorator.validate(name, "name");
        this.url = notEmptyDecorator.validate(url, "url");
        this.product = nonNullDecorator.validate(product, "product");
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
    public String getUrl() {
        return url;
    }

    @Override
    public IProduct getProduct() {
        return product;
    }

}
