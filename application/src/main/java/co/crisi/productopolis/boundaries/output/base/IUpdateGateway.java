package co.crisi.productopolis.boundaries.output.base;

public interface IUpdateGateway<ID, T> extends IGateway<ID> {

    T update(ID id, T newInfo);

}
