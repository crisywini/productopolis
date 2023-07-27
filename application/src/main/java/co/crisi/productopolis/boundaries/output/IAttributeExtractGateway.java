package co.crisi.productopolis.boundaries.output;

import co.crisi.productopolis.domain.IAttribute;
import java.util.List;

public interface IAttributeExtractGateway extends IAttributeGateway {

    IAttribute getById(Long id);

    List<IAttribute> getAll();

}
