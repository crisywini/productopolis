package co.crisi.productopolis.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder(toBuilder = true)
@Schema(description = "Product model info")
public record ProductResponse(Long id, String name, Boolean isActive) {

}
