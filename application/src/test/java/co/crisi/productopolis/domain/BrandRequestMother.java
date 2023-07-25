package co.crisi.productopolis.domain;

import co.crisi.productopolis.model.request.BrandRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BrandRequestMother {


    public BrandRequest random(){
        return new BrandRequest(1L, "Perficient", "A Great Company");
    }

}
