package co.crisi.productopolis.model.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Create Category model info")
public record CategoryRequest(String name, String description) {

}
