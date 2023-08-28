package co.crisi.productopolis.domain.messages;


import java.util.List;

public class OrderProcessed extends Message<List<ProductUpdate>> {

    private final List<ProductUpdate> products;

    public OrderProcessed(List<ProductUpdate> value) {
        super(value);
        this.products = value;
    }

    @Override
    public String getKey() {
        return Messages.ORDER_PROCESSED;
    }

    public List<ProductUpdate> getProducts() {
        return List.copyOf(products);
    }

}
