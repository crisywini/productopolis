package co.crisi.productopolis.domain;

import co.crisi.productopolis.domain.validator.decorator.DateValidatorDecorator;
import co.crisi.productopolis.domain.validator.decorator.NonNegativeNumberValidatorDecorator;
import co.crisi.productopolis.domain.validator.decorator.NonNullValidatorDecorator;
import co.crisi.productopolis.domain.validator.decorator.ValidatorImpl;
import java.time.LocalDate;


public record Product(Long id, String name, String description, Double price, Long stock,
                      LocalDate creationDate, LocalDate lastUpdated, Boolean isFeatured, Boolean isActive,
                      IBrand brand) implements IProduct {

    public Product(Long id, String name, String description, Double price, Long stock,
            LocalDate creationDate, LocalDate lastUpdated, Boolean isFeatured, Boolean isActive,
            IBrand brand){
        var validator = new ValidatorImpl();
        var nullValidator = new NonNullValidatorDecorator(validator);
        this.id = nullValidator.validate(id, "id");
        this.name = nullValidator.validate(name, "name");
        this.description = nullValidator.validate(description, "description");
        var negativeNumberValidator = new NonNegativeNumberValidatorDecorator(nullValidator);
        this.price = negativeNumberValidator.validate(price, "price");
        this.stock = negativeNumberValidator.validate(stock, "stock");
        var dateValidator = new DateValidatorDecorator(nullValidator);
        this.creationDate = dateValidator.validate(creationDate, "creationDate");
        this.lastUpdated = dateValidator.validate(lastUpdated, "creationDate");
        this.isFeatured = isFeatured;
        this.isActive = isActive;
        this.brand = brand;
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
    public Double getPrice() {
        return price;
    }

    @Override
    public Long getStock() {
        return stock;
    }

    @Override
    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public IBrand getBrand() {
        return brand;
    }

}
