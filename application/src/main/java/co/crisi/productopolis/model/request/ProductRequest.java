package co.crisi.productopolis.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
@Schema(description = "Create Product model info")
public record ProductRequest(Long id, String name, String description, Double price, Long stock, Boolean isFeatured,
                             Boolean isActive, Long brandId, List<Long> attributeIds, List<Long> categoryIds,
                             List<Long> imageIds, List<Long> reviewsIds) {

}
