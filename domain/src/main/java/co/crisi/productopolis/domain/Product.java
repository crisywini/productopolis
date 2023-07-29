package co.crisi.productopolis.domain;

import co.crisi.productopolis.domain.validator.decorator.DateValidatorDecorator;
import co.crisi.productopolis.domain.validator.decorator.NonNegativeNumberValidatorDecorator;
import co.crisi.productopolis.domain.validator.decorator.NonNullValidatorDecorator;
import co.crisi.productopolis.domain.validator.decorator.NotEmptyValidatorDecorator;
import co.crisi.productopolis.domain.validator.impl.ValidatorImpl;
import java.time.LocalDate;
import java.util.List;


public record Product(Long id, String name, String description, Double price, Long stock,
                      LocalDate creationDate, LocalDate lastUpdated, Boolean isFeatured, Boolean isActive,
                      IBrand brand, List<IAttribute> attributes, List<ICategory> categories,
                      List<IReview> reviews, List<IImage> images) implements IProduct {

    public Product(Long id, String name, String description, Double price, Long stock,
            LocalDate creationDate, LocalDate lastUpdated, Boolean isFeatured, Boolean isActive,
            IBrand brand, List<IAttribute> attributes, List<ICategory> categories,
            List<IReview> reviews, List<IImage> images) {
        var validator = new ValidatorImpl();
        var nullValidator = new NonNullValidatorDecorator(validator);
        var negativeNumberValidator = new NonNegativeNumberValidatorDecorator(nullValidator);
        var dateValidator = new DateValidatorDecorator(nullValidator);
        var notEmptyValidator = new NotEmptyValidatorDecorator(nullValidator);
        this.id = id;
        this.name = notEmptyValidator.validate(name, "name");
        this.description = notEmptyValidator.validate(description, "description");
        this.price = negativeNumberValidator.validate(price, "price");
        this.stock = negativeNumberValidator.validate(stock, "stock");
        this.creationDate = dateValidator.validate(creationDate, "creation date");
        this.lastUpdated = lastUpdated;
        this.isFeatured = isFeatured;
        this.isActive = isActive;
        this.brand = brand;
        this.attributes = attributes;
        this.categories = categories;
        this.reviews = reviews;
        this.images = images;
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

    @Override
    public List<IAttribute> getAttributes() {
        return attributes;
    }

    @Override
    public List<ICategory> getCategories() {
        return categories;
    }

    @Override
    public List<IReview> getReviews() {
        return reviews;
    }

    @Override
    public List<IImage> getImages() {
        return images;
    }

}
