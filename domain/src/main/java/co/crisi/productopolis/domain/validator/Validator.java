package co.crisi.productopolis.domain.validator;

public interface Validator {

    <T extends Object> T validate(T object, String fieldName);

}
