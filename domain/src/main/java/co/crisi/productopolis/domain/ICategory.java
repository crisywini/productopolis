package co.crisi.productopolis.domain;

import java.time.LocalDate;

public interface ICategory {

    Long getId();

    String getName();

    String getDescription();

    LocalDate getCreationDate();

    LocalDate getLastUpdated();


}
