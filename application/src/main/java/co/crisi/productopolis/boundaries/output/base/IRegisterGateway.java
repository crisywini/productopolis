package co.crisi.productopolis.boundaries.output.base;

public interface IRegisterGateway<ID, T> extends IGateway<ID> {

    void save(T entity);

}
