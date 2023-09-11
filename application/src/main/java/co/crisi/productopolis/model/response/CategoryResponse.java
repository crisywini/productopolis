package co.crisi.productopolis.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Category model info")
public record CategoryResponse(Long id, String name, String description, LocalDate creationDate,
                               LocalDate lastUpdated) {

}
