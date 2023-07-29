package co.crisi.productopolis.domain;

import co.crisi.productopolis.model.request.AttributeRequest;
import co.crisi.productopolis.model.request.AttributeUpdateRequest;
import java.util.Random;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AttributeUpdateRequestMother {

    private final Random random = new Random();

    public AttributeUpdateRequest random() {
        var id= random.nextLong(0, 10);
        return new AttributeUpdateRequest(id, randomRequest());
    }

    public AttributeRequest randomRequest() {
        return new AttributeRequest("Weight", "The weight of the product", "12L");
    }


}
