package co.crisi.productopolis.domain.factory.impl;

import co.crisi.productopolis.domain.Brand;
import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.domain.exception.EmptyStringException;
import co.crisi.productopolis.domain.exception.NegativeNumberException;
import co.crisi.productopolis.domain.factory.IProductFactory;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProductFactoryTest {

    private final IProductFactory factory = new ProductFactory();


    @Test
    void createWhenCorrectFields_shouldCreate() {

        var product = factory.create(1L, "Ipad", "A great device", 7000000.0, 2L, true, true,
                new Brand(1L, "Apple", "Great Company", new ArrayList<>()), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>());

        assertThat(product)
                .isNotNull();

    }

    @Test
    void createWhenIncorrectFields_shouldThrowException() {

        assertThatThrownBy(() -> {
            var product = factory.create(1L, "", "A great device", 7000000.0, 2L, true, true,
                    new Brand(1L, "Apple", "Great Company", new ArrayList<>()), new ArrayList<>(), new ArrayList<>(),
                    new ArrayList<>(), new ArrayList<>());
        })
                .isInstanceOf(EmptyStringException.class)
                .hasMessage("The name should not be empty!");
    }


    @Test
    void createHavingOptionalFieldsAndCorrectFields_shouldCreate() {

        var product = factory.create(1L, "Ipad", "A great device", 7000000.0, 2L, true, true,
                new Brand(1L, "Apple", "Great Company", new ArrayList<>()), new ArrayList<>(), new ArrayList<>());

        assertThat(product)
                .isNotNull()
                .extracting(IProduct::getImages, IProduct::getReviews)
                .contains(Collections.emptyList(), Collections.emptyList());

    }

    @Test
    void createHavingOptionalFieldsAndIncorrectFields_shouldThrowException() {

        assertThatThrownBy(() -> {
            var product = factory.create(1L, "Ipad", "A great device", 7000000.0, -2L, true, true,
                    new Brand(1L, "Apple", "Great Company", new ArrayList<>()), new ArrayList<>(), new ArrayList<>());

        })
                .isInstanceOf(NegativeNumberException.class)
                .hasMessage("The stock should not be negative!");
    }


}