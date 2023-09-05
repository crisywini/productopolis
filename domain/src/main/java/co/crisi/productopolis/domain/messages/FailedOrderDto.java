package co.crisi.productopolis.domain.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FailedOrderDto(@JsonProperty("orderId") Long orderId,
                             @JsonProperty("error") String errorMessage) {

}
