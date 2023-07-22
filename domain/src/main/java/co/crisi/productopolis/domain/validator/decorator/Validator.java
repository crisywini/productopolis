package co.crisi.productopolis.domain.validator.decorator;

public interface Validator {

    <T extends Object> T validate(T object, String fieldName);

}
