package co.crisi.productopolis.config;

import co.crisi.productopolis.config.kafka.OrderFailedSerializer;
import co.crisi.productopolis.domain.messages.OrderFailed;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                OrderFailedSerializer.class);
        props.put("sasl.jaas.config",
                "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"51x8WVctckIWnPCu5yIn5t\" password=\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2F1dGguY29uZHVrdG9yLmlvIiwic291cmNlQXBwbGljYXRpb24iOiJhZG1pbiIsInVzZXJNYWlsIjpudWxsLCJwYXlsb2FkIjp7InZhbGlkRm9yVXNlcm5hbWUiOiI1MXg4V1ZjdGNrSVduUEN1NXlJbjV0Iiwib3JnYW5pemF0aW9uSWQiOjc2MDA0LCJ1c2VySWQiOjg4NDQzLCJmb3JFeHBpcmF0aW9uQ2hlY2siOiI4NmJkZDYwZi1jMDZhLTQ3ODUtODNhMy0yNzkzODQ1ZmQ2ZGUifX0.nLaK4Z_rSwGA-aPO8SU2JYfJTpLxUqvAe3sev5yufMA\";");
        props.put("sasl.mechanism", "PLAIN");
        props.put("security.protocol", "SASL_SSL");
        return props;
    }

    @Bean
    public ProducerFactory<String, OrderFailed> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, OrderFailed> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}