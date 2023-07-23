package co.crisi.productopolis.model.request.register;

import java.util.List;

public record ProductRequest(Long id, String name, String description, Double price, Long stock, Boolean isFeatured,
                             Boolean isActive, Long brandId, List<Long> attributeIds, List<Long> categoryIds) {

}
