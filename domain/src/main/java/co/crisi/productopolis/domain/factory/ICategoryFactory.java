package co.crisi.productopolis.domain.factory;

import co.crisi.productopolis.domain.ICategory;
import java.time.LocalDate;

public interface ICategoryFactory {

    ICategory create(Long id, String name, String description);

    ICategory create(Long id, String name, String description, LocalDate lastUpdated);

}
