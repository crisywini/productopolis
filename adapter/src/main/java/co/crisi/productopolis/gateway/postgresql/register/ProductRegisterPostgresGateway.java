package co.crisi.productopolis.gateway.postgresql.register;

import co.crisi.productopolis.boundaries.output.IProductRegisterGateway;
import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.repository.ProductJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductRegisterPostgresGateway implements IProductRegisterGateway {

    @Autowired
    private ProductJpaRepository repository;

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public IProduct save(IProduct entity) {
        return null;
    }

}
