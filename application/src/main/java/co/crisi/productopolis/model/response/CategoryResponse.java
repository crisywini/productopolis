package co.crisi.productopolis.model.response;

import java.time.LocalDate;

public record CategoryResponse(Long id, String name, String description, LocalDate creationDate,
                               LocalDate lastUpdated) {

}
