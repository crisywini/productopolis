package co.crisi.productopolis.model.response.extract;

import co.crisi.productopolis.model.response.register.ProductResponse;
import java.util.List;

public record BrandResponse(Long id, String name, String descriptions, List<ProductResponse> products) {

}
