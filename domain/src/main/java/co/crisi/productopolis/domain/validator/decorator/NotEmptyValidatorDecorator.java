package co.crisi.productopolis.domain.validator.decorator;

import co.crisi.productopolis.domain.exception.EmptyStringException;
import co.crisi.productopolis.domain.exception.NotCorrectTypeException;
import java.util.Optional;

public class NotEmptyValidatorDecorator extends ValidatorDecorator {

    public NotEmptyValidatorDecorator(Validator objectValidator) {
        super(objectValidator);
    }


    @Override
    public <T> T validate(T object, String fieldName) {
        var validate = this.objectValidator.validate(object, fieldName);
        String t = switch (validate) {
            case String s -> s;
            default -> throw new NotCorrectTypeException("Not a string!");
        };
        var errorMessage = "The %s is empty!";
        return (T) Optional.of(t)
                .filter(v -> !v.isEmpty())
                .orElseThrow(() -> new EmptyStringException(String.format(errorMessage, fieldName)));
    }

}
