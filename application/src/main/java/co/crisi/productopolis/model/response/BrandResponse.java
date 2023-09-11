package co.crisi.productopolis.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;

@Builder(toBuilder = true)
@Schema(description = "Brand model info")
public record BrandResponse(Long id, String name, String description, List<ProductResponse> products) {

}
