package co.crisi.productopolis.boundaries.output.base;

public interface IGateway<ID>  {

    boolean existsById(ID id);

}
