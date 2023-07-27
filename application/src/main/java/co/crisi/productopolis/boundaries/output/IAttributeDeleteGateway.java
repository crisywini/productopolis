package co.crisi.productopolis.boundaries.output;

public interface IAttributeDeleteGateway extends IAttributeGateway {

    void deleteById(Long id);

}
