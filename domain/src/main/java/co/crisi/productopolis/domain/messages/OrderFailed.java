package co.crisi.productopolis.domain.messages;

public class OrderFailed extends Message<FailedOrderDto> {

    private final FailedOrderDto failedOrder;

    public OrderFailed(FailedOrderDto failedOrder) {
        super(failedOrder);
        this.failedOrder = failedOrder;
    }

    @Override
    public String getKey() {
        return Messages.ORDER_FAILED;
    }

}
