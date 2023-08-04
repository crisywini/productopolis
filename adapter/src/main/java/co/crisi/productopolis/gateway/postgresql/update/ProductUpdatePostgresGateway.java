package co.crisi.productopolis.gateway.postgresql.update;

import co.crisi.productopolis.boundaries.output.IProductUpdateGateway;
import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.repository.ProductJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductUpdatePostgresGateway implements IProductUpdateGateway {

    @Autowired
    private ProductJpaRepository repository;

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public IProduct update(Long aLong, IProduct newInfo) {
        return null;
    }

}
