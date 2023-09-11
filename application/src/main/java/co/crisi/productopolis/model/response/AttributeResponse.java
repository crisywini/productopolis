package co.crisi.productopolis.model.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Attribute model info")
public record AttributeResponse(Long id, String name, String description, String value) {

}
