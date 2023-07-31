package co.crisi.productopolis.model.response;

import lombok.Builder;

@Builder(toBuilder = true)
public record ProductResponse(Long id, String name, Boolean isActive) {

}
