package co.crisi.productopolis.model.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Update Category model info")
public record CategoryUpdateRequest(Long id, CategoryRequest newInfo) {

}
