package co.crisi.productopolis.gateway.postgresql.update;

import co.crisi.productopolis.boundaries.output.ICategoryUpdateGateway;
import co.crisi.productopolis.domain.ICategory;
import co.crisi.productopolis.gateway.jpamodel.mapper.CategoryJpaMapper;
import co.crisi.productopolis.repository.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryUpdatePostgresGateway implements ICategoryUpdateGateway {

    private final CategoryJpaRepository repository;

    private final CategoryJpaMapper mapper = Mappers.getMapper(CategoryJpaMapper.class);

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public ICategory update(Long id, ICategory newInfo) {
        var entity = mapper.map(newInfo);
        var entityUpdated = repository.save(entity);
        return mapper.map(entityUpdated);
    }

}
