package co.crisi.productopolis.domain.messages;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;


@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderProcessed extends Message<List<ProductUpdate>> {

    private final List<ProductUpdate> products;

    public OrderProcessed(
            @JsonProperty("products")
                    List<ProductUpdate> products) {
        super(products);
        this.products = products;
    }

    @Override
    public String getKey() {
        return Messages.ORDER_PROCESSED;
    }

    public List<ProductUpdate> getProducts() {
        return List.copyOf(products);
    }

}
