package co.crisi.productopolis.domain;

import co.crisi.productopolis.model.request.ProductUpdateQuantityRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductUpdateQuantityRequestMother {

    public ProductUpdateQuantityRequest random() {
        return new ProductUpdateQuantityRequest(1L, 10);
    }

}
