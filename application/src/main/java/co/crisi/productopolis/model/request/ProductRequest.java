package co.crisi.productopolis.model.request;

import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public record ProductRequest(Long id, String name, String description, Double price, Long stock, Boolean isFeatured,
                             Boolean isActive, Long brandId, List<Long> attributeIds, List<Long> categoryIds,
                             List<Long> imageIds, List<Long> reviewsIds) {

}
