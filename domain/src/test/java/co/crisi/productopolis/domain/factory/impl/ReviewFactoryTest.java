package co.crisi.productopolis.domain.factory.impl;

import co.crisi.productopolis.domain.exception.NullFieldException;
import co.crisi.productopolis.domain.factory.IReviewFactory;
import co.crisi.productopolis.domain.objectmother.ProductMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ReviewFactoryTest {

    private final IReviewFactory factory = new ReviewFactory();

    @Test
    void createWithCorrectFields_shouldCreate() {
        var review = factory.create(1L, 5, "great product", ProductMother.random(), 1L);
        assertThat(review)
                .isNotNull();
    }

    @Test
    void createWithIncorrectFields_shouldThrowException(){
        var throwable = catchThrowable(() -> {
            var review = factory.create(1L, 5, "great null product", null, 1L);
        });

        assertThat(throwable)
                .isInstanceOf(NullFieldException.class)
                .hasMessage("The product should exists!");
    }

}