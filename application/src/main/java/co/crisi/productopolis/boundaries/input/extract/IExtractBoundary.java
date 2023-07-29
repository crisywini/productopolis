package co.crisi.productopolis.boundaries.input.extract;

import co.crisi.productopolis.exception.BusinessException;
import java.util.List;

public interface IExtractBoundary<ID, R, E extends BusinessException> {

    R getById(ID id) throws E;

    List<R> getAll();

}
