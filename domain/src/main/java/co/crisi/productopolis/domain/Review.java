package co.crisi.productopolis.domain;

import co.crisi.productopolis.domain.validator.decorator.DateValidatorDecorator;
import co.crisi.productopolis.domain.validator.decorator.NonNegativeNumberValidatorDecorator;
import co.crisi.productopolis.domain.validator.decorator.NonNullValidatorDecorator;
import co.crisi.productopolis.domain.validator.decorator.NotEmptyValidatorDecorator;
import co.crisi.productopolis.domain.validator.decorator.ValidatorImpl;
import java.time.LocalDate;

public record Review(Long id, Integer rating, String message, IProduct product, Long userId,
                     LocalDate creationDate) implements IReview {


    public Review(Long id, Integer rating, String message, IProduct product, Long userId,
            LocalDate creationDate) {
        var validator = new ValidatorImpl();
        var nonNullDecorator = new NonNullValidatorDecorator(validator);
        var notEmptyDecorator = new NotEmptyValidatorDecorator(nonNullDecorator);
        var nonNegativeDecorator = new NonNegativeNumberValidatorDecorator(nonNullDecorator);
        var validDateDecorator = new DateValidatorDecorator(nonNullDecorator);
        this.id = nonNullDecorator.validate(id, "id");
        this.rating = nonNegativeDecorator.validate(rating, "rating");
        this.message = notEmptyDecorator.validate(message, "message");
        this.product = nonNullDecorator.validate(product, "product");
        this.userId = nonNullDecorator.validate(userId, "user id");
        this.creationDate = validDateDecorator.validate(creationDate, "creation date");
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Integer getRating() {
        return rating;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public IProduct getProduct() {
        return product;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public LocalDate getCreationDate() {
        return creationDate;
    }

}
