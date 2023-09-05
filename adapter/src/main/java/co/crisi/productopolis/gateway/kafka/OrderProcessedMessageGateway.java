package co.crisi.productopolis.gateway.kafka;

import co.crisi.productopolis.boundaries.input.update.IProductUpdateQuantityBoundary;
import co.crisi.productopolis.boundaries.output.message.IReceiveMessageGateway;
import co.crisi.productopolis.boundaries.output.message.ISendMessageGateway;
import co.crisi.productopolis.domain.messages.FailedOrderDto;
import co.crisi.productopolis.domain.messages.OrderFailed;
import co.crisi.productopolis.domain.messages.OrderProcessed;
import co.crisi.productopolis.domain.messages.Topics;
import co.crisi.productopolis.exception.IncorrectProductStockException;
import co.crisi.productopolis.exception.ProductNotFoundException;
import co.crisi.productopolis.model.request.ProductUpdateQuantityRequest;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderProcessedMessageGateway implements IReceiveMessageGateway<OrderProcessed> {

    private final IProductUpdateQuantityBoundary productUpdateBoundary;

    private final ISendMessageGateway<OrderFailed> orderFailedMessageGateway;

    @Override
    @KafkaListener(id = "myId", topics = Topics.THIRD_TOPIC)
    @Transactional
    public void listen(OrderProcessed orderProcessed) {
        log.debug("Received message: {}", orderProcessed.getKey());
        var request = orderProcessed.getOrder().products()
                .stream()
                .map(p -> new ProductUpdateQuantityRequest(p.id(), p.quantity()))
                .collect(Collectors.toList());
        try {
            request.forEach(productUpdateBoundary::updateQuantity);
        } catch (ProductNotFoundException | IncorrectProductStockException e) {
            var failedOrderDto = new FailedOrderDto(orderProcessed.getOrder().orderId(), e.getMessage());
            var orderFailedMessage = new OrderFailed(failedOrderDto);
            orderFailedMessageGateway.sendMessage(orderFailedMessage);
            log.debug("Messaged processed with error!");
        }
        log.debug("Messaged processed successfully!");
    }

}
