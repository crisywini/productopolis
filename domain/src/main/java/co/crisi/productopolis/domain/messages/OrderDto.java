package co.crisi.productopolis.domain.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record OrderDto(@JsonProperty("orderId") Long orderId,
                       @JsonProperty("products") List<ProductUpdateDto> products) {


}
