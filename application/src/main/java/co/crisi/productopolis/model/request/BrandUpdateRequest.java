package co.crisi.productopolis.model.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Update Brand model info")
public record BrandUpdateRequest(Long brandId, BrandRequest newBrandInfo) {

}
