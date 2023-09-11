package co.crisi.productopolis.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder(toBuilder = true)
@Schema(description = "Update Product model info")
public record ProductUpdateRequest(Long productId, ProductRequest newInfo) {

}
