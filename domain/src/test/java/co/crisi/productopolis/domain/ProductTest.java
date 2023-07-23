package co.crisi.productopolis.domain;

import co.crisi.productopolis.domain.exception.EmptyStringException;
import co.crisi.productopolis.domain.exception.NegativeNumberException;
import co.crisi.productopolis.domain.exception.NullFieldException;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ProductTest {

    @Test
    void validProductTest() {
        IProduct product = new Product(1L, "IPad", "Great device", 6000000.0, 50L, LocalDate.now(), LocalDate.now(),
                false, true, new Brand(1L, "Apple", "The greate one", new ArrayList<>()), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        assertThat(product)
                .isNotNull();

    }

    @Test
    void nonValidProductForIdNullTest(){
        var throwable = catchThrowable(() -> {
            IProduct product = new Product(null, "IPad", "Great device", 6000000.0, 50L, LocalDate.now(), LocalDate.now(),
                    false, true, new Brand(1L, "Apple", "The greate one", new ArrayList<>()), new ArrayList<>(),
                    new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        });

        assertThat(throwable)
                .isInstanceOf(NullFieldException.class)
                .hasMessage("The id should exists!");
    }


    @Test
    void nonValidProductForEmptyNameTest(){
        var throwable = catchThrowable(() -> {
            IProduct product = new Product(1L, "", "Great device", 6000000.0, 50L, LocalDate.now(), LocalDate.now(),
                    false, true, new Brand(1L, "Apple", "The greate one", new ArrayList<>()), new ArrayList<>(),
                    new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        });

        assertThat(throwable)
                .isInstanceOf(EmptyStringException.class)
                .hasMessage("The name should not be empty!");
    }

    @Test
    void nonValidProductForNegativePriceTest(){
        var throwable = catchThrowable(() -> {
            IProduct product = new Product(1L, "Ipad", "Great device", -6000000.0, 50L, LocalDate.now(), LocalDate.now(),
                    false, true, new Brand(1L, "Apple", "The greate one", new ArrayList<>()), new ArrayList<>(),
                    new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        });

        assertThat(throwable)
                .isInstanceOf(NegativeNumberException.class)
                .hasMessage("The price should not be negative!");
    }

    @Test
    void nonValidProductForNullPriceTest(){
        var throwable = catchThrowable(() -> {
            IProduct product = new Product(1L, "Ipad", "Great device", null, 50L, LocalDate.now(), LocalDate.now(),
                    false, true, new Brand(1L, "Apple", "The greate one", new ArrayList<>()), new ArrayList<>(),
                    new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        });

        assertThat(throwable)
                .isInstanceOf(NullFieldException.class)
                .hasMessage("The price should exists!");
    }

    @Test
    void nonValidProductForCreationDateNullTest(){
        var throwable = catchThrowable(() -> {
            IProduct product = new Product(1L, "Ipad", "Great device", 6000000.0, 50L, null, LocalDate.now(),
                    false, true, new Brand(1L, "Apple", "The greate one", new ArrayList<>()), new ArrayList<>(),
                    new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        });

        assertThat(throwable)
                .isInstanceOf(NullFieldException.class)
                .hasMessage("The creation date should exists!");
    }






}