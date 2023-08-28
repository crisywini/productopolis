package co.crisi.productopolis.gateway.kafka;

import co.crisi.productopolis.domain.messages.Topics;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(id = "myId", topics = Topics.THIRD_TOPIC)
    public void listen(String in) {
        System.out.println(in);
    }

}
