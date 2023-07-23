package co.crisi.productopolis.domain.factory.impl;

import co.crisi.productopolis.domain.exception.EmptyStringException;
import co.crisi.productopolis.domain.factory.IAttributeFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.assertThat;

class AttributeFactoryTest {

    private IAttributeFactory factory = new AttributeFactory();

    @Test
    void createWithCorrectFields_shouldCreate() {
        var attribute = factory.create(1L, "Weight", "The Weight of the cellphone", "23.0");

        assertThat(attribute)
                .isNotNull();
    }

    @Test
    void createWithIncorrectFields_shouldThrowsException() {
        var throwable = catchThrowable(() -> {
            var attribute = factory.create(1L, "Weight", "The Weight of the cellphone", "");
        });

        assertThat(throwable)
                .isInstanceOf(EmptyStringException.class)
                .hasMessage("The value should not be empty!");
    }

}