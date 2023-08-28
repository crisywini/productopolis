package co.crisi.productopolis.config.kafka;

import co.crisi.productopolis.domain.messages.OrderProcessed;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;

public class OrderProcessedDeserializer implements Deserializer<OrderProcessed> {

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {

    }

    @Override
    public OrderProcessed deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        OrderProcessed orderProcessed = null;
        try {
            orderProcessed = mapper.readValue(arg1, OrderProcessed.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderProcessed;
    }

}
