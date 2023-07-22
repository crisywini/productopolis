package co.crisi.productopolis.domain.validator.decorator;

import co.crisi.productopolis.domain.exception.IllegalDateException;
import co.crisi.productopolis.domain.exception.NotCorrectTypeException;
import java.time.LocalDate;
import java.util.Optional;

public class DateValidatorDecorator extends ValidatorDecorator {

    public DateValidatorDecorator(Validator objectValidator) {
        super(objectValidator);
    }

    @Override
    public <T> T validate(T object, String fieldName) {
        var validate = this.objectValidator.validate(object, fieldName);
        var asDate = switch (validate) {
            case LocalDate l -> l;
            default -> throw new NotCorrectTypeException("Not a date!");
        };
        var errorMessage = "The %s should be a valid date!";
        return (T) Optional.of(asDate)
                .filter(date -> date.isAfter(LocalDate.MIN))
                .orElseThrow(() -> new IllegalDateException(String.format(errorMessage, fieldName)));
    }

}
