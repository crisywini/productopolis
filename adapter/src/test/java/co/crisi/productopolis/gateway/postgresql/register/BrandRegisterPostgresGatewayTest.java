package co.crisi.productopolis.gateway.postgresql.register;

import co.crisi.productopolis.boundaries.output.IBrandRegisterGateway;
import co.crisi.productopolis.repository.BrandJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class BrandRegisterPostgresGatewayTest {


    @Mock
    private BrandJpaRepository repository;

    @InjectMocks
    private BrandRegisterPostgresGateway gateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void existsById_whenNoExistingEntity_shouldReturnFalse() {
        var id = 1L;

        given(repository.existsById(id))
                .willReturn(false);

        var exists = gateway.existsById(id);

        assertThat(exists)
                .isFalse();
    }

    @Test
    void save() {
    }

}