package co.crisi.productopolis.domain.factory.impl;

import co.crisi.productopolis.domain.exception.NullFieldException;
import co.crisi.productopolis.domain.factory.IImageFactory;
import co.crisi.productopolis.domain.objectmother.ProductMother;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ImageFactoryTest {

    private IImageFactory factory = new ImageFactory();


    @Test
    void createWithCorrectFields_shouldCreate() {
        var image = factory.create(1L, "image_from_side.jpeg", "/somewhere/in/a/linux/system/hopefully.jpeg",
                ProductMother.random());
        assertThat(image)
                .isNotNull();
    }

    @Test
    void createWithIncorrectFields_shouldThrowsException() {
        var throwable = catchThrowable(() -> {
            var image = factory.create(1L, "image_from_side.jpeg", "/somewhere/in/a/linux/system/hopefully.jpeg",
                    null);
        });

        assertThat(throwable)
                .isInstanceOf(NullFieldException.class)
                .hasMessage("The product should exists!");

    }

}