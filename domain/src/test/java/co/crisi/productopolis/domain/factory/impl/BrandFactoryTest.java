package co.crisi.productopolis.domain.factory.impl;

import co.crisi.productopolis.domain.IBrand;
import co.crisi.productopolis.domain.exception.EmptyStringException;
import co.crisi.productopolis.domain.factory.IBrandFactory;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class BrandFactoryTest {

    private final IBrandFactory factory = new BrandFactory();

    @Test
    void fullCreateWithCorrectFields_shouldCreate() {
        var brand = factory.create(1L, "Apple", "Great Company", new ArrayList<>());
        assertThat(brand)
                .isNotNull();
    }

    @Test
    void fullCreateWithIncorrectFields_shouldThrowsException() {
        var throwable = catchThrowable(() -> {
            var brand = factory.create(1L, "Apple", "", new ArrayList<>());
        });
        assertThat(throwable)
                .isInstanceOf(EmptyStringException.class)
                .hasMessage("The description should not be empty!");
    }

    @Test
    void createWithOptionalCorrectFields_shouldCreate() {
        var brand = factory.create(1L, "Intellij", "Great IDE");
        assertThat(brand)
                .isNotNull()
                .extracting(IBrand::getProducts)
                .asList()
                .isNotNull()
                .isEmpty();
    }

    @Test
    void createWithOptionalIncorrectFields_shouldThrowsException() {
        var throwable = catchThrowable(() -> {
            var brand = factory.create(1L, "Intellij", "");
        });

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(EmptyStringException.class)
                .hasMessage("The description should not be empty!");
    }

}