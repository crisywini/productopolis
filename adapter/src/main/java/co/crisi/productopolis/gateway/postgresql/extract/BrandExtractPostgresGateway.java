package co.crisi.productopolis.gateway.postgresql.extract;

import co.crisi.productopolis.boundaries.output.IBrandExtractGateway;
import co.crisi.productopolis.domain.IBrand;
import co.crisi.productopolis.exception.BrandNotFoundException;
import co.crisi.productopolis.gateway.jpamodel.mapper.BrandJpaMapper;
import co.crisi.productopolis.repository.BrandJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BrandExtractPostgresGateway implements IBrandExtractGateway {

    private final BrandJpaRepository repository;

    private final BrandJpaMapper mapper = Mappers.getMapper(BrandJpaMapper.class);

    @Override
    public IBrand getById(Long id) {
        var brand = repository.findById(id).orElseThrow(
                () -> new BrandNotFoundException(String.format("The brand with id %d was not found!", id)));
        return mapper.map(brand);
    }

    @Override
    public List<IBrand> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

}
