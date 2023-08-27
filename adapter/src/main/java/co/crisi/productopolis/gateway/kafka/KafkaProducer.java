package co.crisi.productopolis.gateway.kafka;

import co.crisi.productopolis.boundaries.output.ISendMessageGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaProducer implements ISendMessageGateway {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage(String message) {

        var future = kafkaTemplate.send("third_topic", message);
        log.info("message sent...");

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.debug("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                log.error("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });

        kafkaTemplate.flush();
    }

}
