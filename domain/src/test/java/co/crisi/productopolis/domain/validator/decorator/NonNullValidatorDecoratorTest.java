package co.crisi.productopolis.domain.validator.decorator;

import co.crisi.productopolis.domain.exception.NullFieldException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NonNullValidatorDecoratorTest {

    private final Validator validator = new ValidatorImpl();


    @ParameterizedTest
    @ValueSource(strings = {"Field", "", "another field"})
    void testStringField(String field) {
        var decorator = new NonNullValidatorDecorator(validator);

        var element = decorator.validate(field, "field");

        assertThat(element)
                .isNotNull()
                .isEqualTo(field);
    }

    @ParameterizedTest
    @NullSource
    void testNullStringField(String field) {
        var decorator = new NonNullValidatorDecorator(validator);

        assertThatThrownBy(() -> decorator.validate(field, "field"))
                .isInstanceOf(NullFieldException.class)
                .hasMessage("The field should exists!");
    }

}