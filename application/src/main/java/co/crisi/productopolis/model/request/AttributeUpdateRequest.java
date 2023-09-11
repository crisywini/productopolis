package co.crisi.productopolis.model.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Update Attribute model info")
public record AttributeUpdateRequest(Long attributeId, AttributeRequest attributeInfo) {

}
