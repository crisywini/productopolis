package co.crisi.productopolis.domain.validator.decorator;

import co.crisi.productopolis.domain.exception.IllegalDateException;
import co.crisi.productopolis.domain.validator.Validator;
import co.crisi.productopolis.domain.validator.impl.ValidatorImpl;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DateValidatorDecoratorTest {

    private final Validator validator = new ValidatorImpl();

    @Test
    void validateLegalDateTest() {

        LocalDate date = LocalDate.of(2023, 5, 15);
        var dateValidator = new DateValidatorDecorator(validator);

        var validate = dateValidator.validate(date, "date");

        assertThat(validate)
                .isNotNull()
                .isAfter(LocalDate.MIN);
    }

    @Test
    void validateIllegalDateTest() {

        LocalDate date = LocalDate.MIN;
        var dateValidator = new DateValidatorDecorator(validator);

        assertThatThrownBy(() -> dateValidator.validate(date, "date"))
                .isInstanceOf(IllegalDateException.class);
    }

}