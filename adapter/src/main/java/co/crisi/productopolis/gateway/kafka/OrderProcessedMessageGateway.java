package co.crisi.productopolis.gateway.kafka;

import co.crisi.productopolis.boundaries.input.update.IProductUpdateBoundary;
import co.crisi.productopolis.boundaries.input.update.IProductUpdateQuantityBoundary;
import co.crisi.productopolis.boundaries.output.message.IReceiveMessageGateway;
import co.crisi.productopolis.domain.messages.OrderProcessed;
import co.crisi.productopolis.domain.messages.Topics;
import co.crisi.productopolis.model.request.ProductUpdateQuantityRequest;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderProcessedMessageGateway implements IReceiveMessageGateway<OrderProcessed> {

    private final IProductUpdateQuantityBoundary productUpdateBoundary;

    @Override
    @KafkaListener(id = "myId", topics = Topics.THIRD_TOPIC)
    public void listen(OrderProcessed orderProcessed) {
        log.debug("Received message: {}", orderProcessed.getKey());
        var request = orderProcessed.getProducts()
                .stream()
                .map(p -> new ProductUpdateQuantityRequest(p.id(), p.quantity()))
                .collect(Collectors.toList());

        request.forEach(productUpdateBoundary::updateQuantity);
        log.debug("Messaged processed!");
    }

}
