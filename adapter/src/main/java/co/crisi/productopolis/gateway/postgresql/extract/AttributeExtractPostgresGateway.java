package co.crisi.productopolis.gateway.postgresql.extract;

import co.crisi.productopolis.boundaries.output.IAttributeExtractGateway;
import co.crisi.productopolis.domain.IAttribute;
import co.crisi.productopolis.repository.AttributeJpaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AttributeExtractPostgresGateway implements IAttributeExtractGateway {

    @Autowired
    private AttributeJpaRepository repository;

    @Override
    public IAttribute getById(Long aLong) {
        return null;
    }

    @Override
    public List<IAttribute> getAll() {
        return null;
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

}
