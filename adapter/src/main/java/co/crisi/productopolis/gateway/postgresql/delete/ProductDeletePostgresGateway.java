package co.crisi.productopolis.gateway.postgresql.delete;

import co.crisi.productopolis.boundaries.output.IProductDeleteGateway;
import co.crisi.productopolis.repository.ProductJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDeletePostgresGateway implements IProductDeleteGateway {

    @Autowired
    private ProductJpaRepository repository;

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

}
