package co.crisi.productopolis.model.request;

import lombok.Builder;

@Builder(toBuilder = true)
public record ProductUpdateRequest(Long productId, ProductRequest newInfo) {

}
