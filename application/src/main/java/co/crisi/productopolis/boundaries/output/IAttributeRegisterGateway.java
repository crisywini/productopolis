package co.crisi.productopolis.boundaries.output;

import co.crisi.productopolis.domain.IAttribute;

public interface IAttributeRegisterGateway extends IAttributeGateway {

    void save(IAttribute attribute);

}
