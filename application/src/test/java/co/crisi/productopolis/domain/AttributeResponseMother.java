package co.crisi.productopolis.domain;

import co.crisi.productopolis.domain.factory.IAttributeFactory;
import co.crisi.productopolis.domain.factory.impl.AttributeFactory;
import co.crisi.productopolis.model.response.AttributeResponse;
import co.crisi.productopolis.model.response.mapper.AttributeMapper;
import java.util.Random;
import lombok.experimental.UtilityClass;
import org.mapstruct.factory.Mappers;

@UtilityClass
public class AttributeResponseMother {

    private final IAttributeFactory factory = new AttributeFactory();

    private final AttributeMapper mapper = Mappers.getMapper(AttributeMapper.class);

    private final Random random = new Random();

    public AttributeResponse random() {
        return mapper.map(factory.create(random.nextLong(), "Weight", "The weight of the product", "1 pound"));
    }

}
