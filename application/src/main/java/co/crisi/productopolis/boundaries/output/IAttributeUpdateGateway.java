package co.crisi.productopolis.boundaries.output;

import co.crisi.productopolis.domain.IAttribute;

public interface IAttributeUpdateGateway extends IAttributeGateway {

    IAttribute update(Long id, IAttribute attributeInfo);

}
