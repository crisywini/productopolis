package co.crisi.productopolis.domain.validator.decorator;

import co.crisi.productopolis.domain.exception.NegativeNumberException;
import co.crisi.productopolis.domain.exception.NotCorrectTypeException;
import co.crisi.productopolis.domain.validator.Validator;
import java.util.Optional;

public class NonNegativeNumberValidatorDecorator extends ValidatorDecorator {

    public NonNegativeNumberValidatorDecorator(Validator objectValidator) {
        super(objectValidator);
    }

    @Override
    public <T> T validate(T object, String fieldName) {
        var validate = this.objectValidator.validate(object, fieldName);
        Object t = switch (validate) {
            case Double d -> (Double) d;
            case Integer i -> (Integer) i;
            case Long l -> (Long )l;
            default -> throw new NotCorrectTypeException("Not a number!");
        };
        var errorMessage = "The %s should not be negative!";
        return (T) Optional.of(t)
                .filter(e -> ((Number) e).floatValue() >= 0)
                .orElseThrow(() -> new NegativeNumberException(String.format(errorMessage, fieldName)));
    }

}
