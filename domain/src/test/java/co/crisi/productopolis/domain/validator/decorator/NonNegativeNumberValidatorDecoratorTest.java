package co.crisi.productopolis.domain.validator.decorator;

import co.crisi.productopolis.domain.exception.NegativeNumberException;
import co.crisi.productopolis.domain.validator.Validator;
import co.crisi.productopolis.domain.validator.impl.ValidatorImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class NonNegativeNumberValidatorDecoratorTest {

    private final Validator validator = new ValidatorImpl();

    @ParameterizedTest
    @ValueSource(doubles = 1.0)
    void validateNonNegativeDoubles(double field) {
        var decorator = new NonNegativeNumberValidatorDecorator(validator);
        var element = decorator.validate(field, "field");
        assertThat(element)
                .isNotNull()
                .isEqualTo(field);
    }

    @ParameterizedTest
    @ValueSource(ints = 1)
    void validateNonNegativeInts(int field) {
        var decorator = new NonNegativeNumberValidatorDecorator(validator);
        var element = decorator.validate(field, "field");
        assertThat(element)
                .isNotNull()
                .isEqualTo(field);
    }

    @ParameterizedTest
    @ValueSource(longs = 1L)
    void validateNonNegativeLongs(long field) {
        var decorator = new NonNegativeNumberValidatorDecorator(validator);
        var element = decorator.validate(field, "field");
        assertThat(element)
                .isNotNull()
                .isEqualTo(field);
    }


    @ParameterizedTest
    @ValueSource(doubles = -1.0)
    void validateNegativeDouble(double field) {
        var decorator = new NonNegativeNumberValidatorDecorator(validator);
        assertThatThrownBy(() -> decorator.validate(field, "field"))
                .isInstanceOf(NegativeNumberException.class)
                .hasMessage("The field should not be negative!");
    }


    @ParameterizedTest
    @ValueSource(ints = -1)
    void validateNegativeInteger(int field) {
        var decorator = new NonNegativeNumberValidatorDecorator(validator);
        assertThatThrownBy(() -> decorator.validate(field, "field"))
                .isInstanceOf(NegativeNumberException.class)
                .hasMessage("The field should not be negative!");
    }

    @ParameterizedTest
    @ValueSource(longs = -1)
    void validateNegativeLong(long field) {
        var decorator = new NonNegativeNumberValidatorDecorator(validator);
        assertThatThrownBy(() -> decorator.validate(field, "field"))
                .isInstanceOf(NegativeNumberException.class)
                .hasMessage("The field should not be negative!");
    }

}