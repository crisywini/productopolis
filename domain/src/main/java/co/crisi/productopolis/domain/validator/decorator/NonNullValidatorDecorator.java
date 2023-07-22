package co.crisi.productopolis.domain.validator.decorator;

import co.crisi.productopolis.domain.exception.NullFieldException;
import java.util.Optional;

public class NonNullValidatorDecorator extends ValidatorDecorator {

    public NonNullValidatorDecorator(Validator objectValidator) {
        super(objectValidator);
    }

    @Override
    public <T> T validate(T object, String fieldName) {
        var validate = super.validate(object, fieldName);
        var errorMessage = "The %s should exists!";
        return Optional.ofNullable(validate)
                .orElseThrow(() -> new NullFieldException(String
                        .format("The %s should exists!", fieldName)));
    }

}
