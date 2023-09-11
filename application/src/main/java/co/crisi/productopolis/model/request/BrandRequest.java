package co.crisi.productopolis.model.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Create brand model info")
public record BrandRequest(Long id, String name, String description) {

}
