package co.crisi.productopolis.boundaries.output;

import co.crisi.productopolis.boundaries.output.base.IRegisterGateway;
import co.crisi.productopolis.domain.IAttribute;

public interface IAttributeRegisterGateway extends IRegisterGateway<Long, IAttribute> {

    boolean existsByName(String name);

}
