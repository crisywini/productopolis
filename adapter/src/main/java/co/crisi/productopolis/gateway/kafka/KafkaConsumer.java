package co.crisi.productopolis.gateway.kafka;

import co.crisi.productopolis.boundaries.output.message.IReceiveMessageGateway;
import co.crisi.productopolis.domain.messages.OrderProcessed;
import co.crisi.productopolis.domain.messages.Topics;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer implements IReceiveMessageGateway<OrderProcessed> {

    @Override
    @KafkaListener(id = "myId", topics = Topics.THIRD_TOPIC)
    public void listen(OrderProcessed orderProcessed) {
        System.out.println(orderProcessed);
        System.out.println(orderProcessed.getKey());
        System.out.println(orderProcessed.getProducts());
    }

}
