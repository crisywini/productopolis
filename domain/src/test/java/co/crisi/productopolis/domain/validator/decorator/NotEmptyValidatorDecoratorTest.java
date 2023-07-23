package co.crisi.productopolis.domain.validator.decorator;

import co.crisi.productopolis.domain.exception.EmptyStringException;
import co.crisi.productopolis.domain.validator.Validator;
import co.crisi.productopolis.domain.validator.impl.ValidatorImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class NotEmptyValidatorDecoratorTest {

    private final Validator validator = new ValidatorImpl();

    @ParameterizedTest
    @ValueSource(strings = {"a", "b"})
    void validStringTest(String field) {
        var notEmptyValidatorDecorator = new NotEmptyValidatorDecorator(validator);
        var validated = notEmptyValidatorDecorator.validate(field, "field");
        assertThat(validated)
                .isNotNull()
                .isEqualTo(field);
    }

    @ParameterizedTest
    @ValueSource(strings = "")
    void invalidStringTest(String field) {
        var notEmptyValidatorDecorator = new NotEmptyValidatorDecorator(validator);
        assertThatThrownBy(() -> notEmptyValidatorDecorator.validate(field, "field"))
                .isInstanceOf(EmptyStringException.class)
                .hasMessage("The field should not be empty!");
    }

}