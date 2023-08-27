package co.crisi.productopolis.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {


    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("third_topic").build();
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configs.put("sasl.jaas.config",
                "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"51x8WVctckIWnPCu5yIn5t\" password=\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2F1dGguY29uZHVrdG9yLmlvIiwic291cmNlQXBwbGljYXRpb24iOiJhZG1pbiIsInVzZXJNYWlsIjpudWxsLCJwYXlsb2FkIjp7InZhbGlkRm9yVXNlcm5hbWUiOiI1MXg4V1ZjdGNrSVduUEN1NXlJbjV0Iiwib3JnYW5pemF0aW9uSWQiOjc2MDA0LCJ1c2VySWQiOjg4NDQzLCJmb3JFeHBpcmF0aW9uQ2hlY2siOiI4NmJkZDYwZi1jMDZhLTQ3ODUtODNhMy0yNzkzODQ1ZmQ2ZGUifX0.nLaK4Z_rSwGA-aPO8SU2JYfJTpLxUqvAe3sev5yufMA\";");
        configs.put("sasl.mechanism", "PLAIN");
        configs.put("security.protocol", "SASL_SSL");
        return new KafkaAdmin(configs);
    }

}