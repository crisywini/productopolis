package co.crisi.productopolis.boundaries.output.base;

public interface IRegisterGateway<ID, T> extends IGateway<ID> {

    T save(T entity);

}
