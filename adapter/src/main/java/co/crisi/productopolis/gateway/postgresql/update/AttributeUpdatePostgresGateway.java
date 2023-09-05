package co.crisi.productopolis.gateway.postgresql.update;

import co.crisi.productopolis.boundaries.output.IAttributeUpdateGateway;
import co.crisi.productopolis.domain.IAttribute;
import co.crisi.productopolis.gateway.jpamodel.mapper.AttributeJpaMapper;
import co.crisi.productopolis.repository.AttributeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AttributeUpdatePostgresGateway implements IAttributeUpdateGateway {

    private final AttributeJpaRepository repository;

    private final AttributeJpaMapper mapper = Mappers.getMapper(AttributeJpaMapper.class);

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public IAttribute update(Long id, IAttribute newInfo) {
        var entity = mapper.map(newInfo);
        var entityUpdated = repository.save(entity);
        return mapper.map(entityUpdated);
    }

}
