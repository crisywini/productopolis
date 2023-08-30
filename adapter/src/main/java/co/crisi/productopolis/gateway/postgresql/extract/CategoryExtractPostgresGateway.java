package co.crisi.productopolis.gateway.postgresql.extract;

import co.crisi.productopolis.boundaries.output.ICategoryExtractGateway;
import co.crisi.productopolis.domain.ICategory;
import co.crisi.productopolis.exception.CategoryNotFoundException;
import co.crisi.productopolis.gateway.jpamodel.mapper.CategoryJpaMapper;
import co.crisi.productopolis.repository.CategoryJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryExtractPostgresGateway implements ICategoryExtractGateway {

    private final CategoryJpaRepository repository;

    private final CategoryJpaMapper mapper = Mappers.getMapper(CategoryJpaMapper.class);

    @Override
    public ICategory getById(Long id) {
        var category = repository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException(String.format("The category with id %d was not found!", id)));
        return mapper.map(category);
    }

    @Override
    public List<ICategory> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

}
