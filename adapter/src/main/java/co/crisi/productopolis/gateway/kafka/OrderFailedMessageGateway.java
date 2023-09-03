package co.crisi.productopolis.gateway.kafka;

import co.crisi.productopolis.boundaries.output.message.ISendMessageGateway;
import co.crisi.productopolis.domain.messages.OrderFailed;
import co.crisi.productopolis.domain.messages.Topics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderFailedMessageGateway implements ISendMessageGateway<OrderFailed> {

    private final KafkaTemplate<String, OrderFailed> kafkaTemplate;


    @Override
    public void sendMessage(OrderFailed message) {
        kafkaTemplate.send(Topics.EXCEPTION, message.getKey(), message);
    }

}
        