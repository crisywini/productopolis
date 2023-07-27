package co.crisi.productopolis.boundaries.output.base;

import java.util.List;

public interface IExtractGateway<ID, T> extends IGateway<ID> {

    T getById(ID id);

    List<T> getAll();

}
