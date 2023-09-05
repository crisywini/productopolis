package co.crisi.productopolis.gateway.postgresql.extract;

import co.crisi.productopolis.boundaries.output.IAttributeExtractGateway;
import co.crisi.productopolis.domain.IAttribute;
import co.crisi.productopolis.exception.AttributeNotFoundException;
import co.crisi.productopolis.gateway.jpamodel.AttributeJpaEntity;
import co.crisi.productopolis.gateway.jpamodel.mapper.AttributeJpaMapper;
import co.crisi.productopolis.repository.AttributeJpaRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AttributeExtractPostgresGateway implements IAttributeExtractGateway {

    private final AttributeJpaRepository repository;

    private final AttributeJpaMapper mapper = Mappers.getMapper(AttributeJpaMapper.class);

    @Override
    public IAttribute getById(Long id) {
        var attribute = repository.findById(id).orElseThrow(
                () -> new AttributeNotFoundException(String.format("The attribute with id %d was not found!", id)));
        return mapper.map(attribute);
    }

    @Override
    public List<IAttribute> getAll() {
        Iterable<AttributeJpaEntity> attributeIterable = repository.findAll();
        return StreamSupport.stream(attributeIterable.spliterator(), false)
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

}
