package co.crisi.productopolis.domain.factory.impl;

import co.crisi.productopolis.domain.ICategory;
import co.crisi.productopolis.domain.exception.EmptyStringException;
import co.crisi.productopolis.domain.exception.IllegalDateException;
import co.crisi.productopolis.domain.factory.ICategoryFactory;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class CategoryFactoryTest {

    private final ICategoryFactory factory = new CategoryFactory();

    @Test
    void createWhenCompleteCorrectFields_shouldCreate() {
        var category = factory.create(1L, "Books", "Books you can read", LocalDate.of(2023, 1, 6));
        assertThat(category)
                .isNotNull();
    }

    @Test
    void createWhenCompleteIncorrectFields_shouldThrowException() {
        var throwable = catchThrowable(() -> {
            var category = factory.create(1L, "Books", "Books you can read", LocalDate.MIN);
        });

        assertThat(throwable)
                .isInstanceOf(IllegalDateException.class)
                .hasMessage("The last updated should be a valid date!");
    }

    @Test
    void createWhenOptionalFields_shouldCreate() {
        var category = factory.create(1L, "Science Fiction", "Something fictional scientific");
        assertThat(category)
                .isNotNull()
                .extracting(ICategory::getCreationDate, ICategory::getLastUpdated)
                .contains(LocalDate.now(), LocalDate.now());
    }

    @Test
    void createWhenOptionalFieldsButSomeIncorrect_shouldThrowException() {
        var throwable = catchThrowable(() -> {
            var category = factory.create(1L, "Science Fiction", "");
        });

        assertThat(throwable)
                .isInstanceOf(EmptyStringException.class)
                .hasMessage("The description should not be empty!");

    }

}