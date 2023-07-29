package co.crisi.productopolis.boundaries.output.base;

public interface IDeleteGateway<ID> extends IGateway<ID> {

    void deleteById(ID id);

}
