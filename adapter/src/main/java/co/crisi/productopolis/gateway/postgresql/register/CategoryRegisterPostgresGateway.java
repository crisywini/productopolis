package co.crisi.productopolis.gateway.postgresql.register;

import co.crisi.productopolis.boundaries.output.ICategoryRegisterGateway;
import co.crisi.productopolis.domain.ICategory;
import co.crisi.productopolis.gateway.jpamodel.mapper.CategoryJpaMapper;
import co.crisi.productopolis.repository.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CategoryRegisterPostgresGateway implements ICategoryRegisterGateway {

    private final CategoryJpaRepository repository;

    private final CategoryJpaMapper mapper = Mappers.getMapper(CategoryJpaMapper.class);

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public ICategory save(ICategory entity) {
        var category = mapper.map(entity);
        var entitySaved = repository.save(category);
        return mapper.map(entitySaved);
    }

}
