package co.crisi.productopolis.domain;

import co.crisi.productopolis.domain.validator.decorator.DateValidatorDecorator;
import co.crisi.productopolis.domain.validator.decorator.NonNullValidatorDecorator;
import co.crisi.productopolis.domain.validator.decorator.NotEmptyValidatorDecorator;
import co.crisi.productopolis.domain.validator.impl.ValidatorImpl;
import java.time.LocalDate;

public record Category(Long id, String name, String description, LocalDate creationDate,
                       LocalDate lastUpdated) implements ICategory {


    public Category(Long id, String name, String description, LocalDate creationDate,
            LocalDate lastUpdated){
        var validator = new ValidatorImpl();
        var nonNullDecorator = new NonNullValidatorDecorator(validator);
        var validDateDecorator = new DateValidatorDecorator(nonNullDecorator);
        var emptyDecorator = new NotEmptyValidatorDecorator(nonNullDecorator);
        this.id = id;
        this.name = emptyDecorator.validate(name, "name");
        this.description = emptyDecorator.validate(description, "description");
        this.creationDate = validDateDecorator.validate(creationDate, "creation date");
        this.lastUpdated = validDateDecorator.validate(lastUpdated, "last updated");
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
    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

}
