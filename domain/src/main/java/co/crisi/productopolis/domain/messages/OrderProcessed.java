package co.crisi.productopolis.domain.messages;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderProcessed extends Message<OrderDto> {

    private final OrderDto order;

    public OrderProcessed(
            @JsonProperty("order")
                    OrderDto order) {
        super(order);
        this.order = order;
    }

    @Override
    public String getKey() {
        return Messages.ORDER_PROCESSED;
    }

    public OrderDto getOrder() {
        return order;
    }

}
