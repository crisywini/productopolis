package co.crisi.productopolis.gateway.kafka;

import co.crisi.productopolis.boundaries.output.message.ISendMessageGateway;
import co.crisi.productopolis.domain.messages.Topics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderProcessedErrorMessageGateway implements ISendMessageGateway<String> {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage(String message) {
        kafkaTemplate.send(Topics.EXCEPTION, message);
        kafkaTemplate.flush();
    }

}
    