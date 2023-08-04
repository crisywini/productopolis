package co.crisi.productopolis.gateway.postgresql.extract;

import co.crisi.productopolis.boundaries.output.IProductExtractGateway;
import co.crisi.productopolis.domain.IProduct;
import co.crisi.productopolis.repository.ProductJpaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductExtractPostgresGateway implements IProductExtractGateway {

    @Autowired
    private ProductJpaRepository repository;

    @Override
    public IProduct getById(Long id) {
        repository.findById(id);
        return null;
    }

    @Override
    public List<IProduct> getAll() {
        return null;
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

}
