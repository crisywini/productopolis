package co.crisi.productopolis.domain.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductUpdate(@JsonProperty("id") Long id, @JsonProperty("quantity") Integer quantity) {

}
