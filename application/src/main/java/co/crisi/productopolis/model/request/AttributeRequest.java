package co.crisi.productopolis.model.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Create Attribute model info")
public record AttributeRequest(String name, String description, String value) {

}
