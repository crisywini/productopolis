package co.crisi.productopolis.gateway.postgresql.delete;

import co.crisi.productopolis.boundaries.output.IAttributeDeleteGateway;
import co.crisi.productopolis.gateway.jpamodel.mapper.AttributeJpaMapper;
import co.crisi.productopolis.repository.AttributeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AttributeDeletePostgresGateway implements IAttributeDeleteGateway {

    private final AttributeJpaRepository repository;


    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

}
