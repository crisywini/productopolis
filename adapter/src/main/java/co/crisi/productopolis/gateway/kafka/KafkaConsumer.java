package co.crisi.productopolis.gateway.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(id = "myId", topics = "third_topic")
    public void listen(String in) {
        System.out.println(in);
    }

}
