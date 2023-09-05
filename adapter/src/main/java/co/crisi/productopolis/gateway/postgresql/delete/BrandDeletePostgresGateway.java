package co.crisi.productopolis.gateway.postgresql.delete;

import co.crisi.productopolis.boundaries.output.IBrandDeleteGateway;
import co.crisi.productopolis.repository.BrandJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BrandDeletePostgresGateway implements IBrandDeleteGateway {

    private final BrandJpaRepository repository;

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

}
