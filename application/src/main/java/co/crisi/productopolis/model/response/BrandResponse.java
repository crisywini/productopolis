package co.crisi.productopolis.model.response;

import java.util.List;
import lombok.Builder;

@Builder(toBuilder = true)
public record BrandResponse(Long id, String name, String description, List<ProductResponse> products) {

}
