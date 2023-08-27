package co.crisi.productopolis.controller;

import co.crisi.productopolis.annotations.ExperimentalFeature;
import co.crisi.productopolis.gateway.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@ExperimentalFeature
@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class KafkaControllerTest {

    private final KafkaProducer producer;


    @PostMapping
    public ResponseEntity<Void> sendMessage(@RequestBody String message){
        producer.sendMessage(message);

        return ResponseEntity.ok().build();
    }

}
