package co.crisi.productopolis.model.response;

import java.util.List;

public record BrandResponse(Long id, String name, String description, List<ProductResponse> products) {

}
