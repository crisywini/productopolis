package co.crisi.productopolis.domain.validator.decorator;

public class ValidatorImpl implements Validator{

    @Override
    public <T> T validate(T object, String fieldName) {
        return object;
    }

}
