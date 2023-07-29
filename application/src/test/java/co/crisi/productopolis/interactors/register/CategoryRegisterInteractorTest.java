package co.crisi.productopolis.interactors.register;

import co.crisi.productopolis.boundaries.input.register.ICategoryRegisterBoundary;
import co.crisi.productopolis.boundaries.output.ICategoryRegisterGateway;
import co.crisi.productopolis.domain.CategoryRequestMother;
import co.crisi.productopolis.domain.factory.ICategoryFactory;
import co.crisi.productopolis.domain.factory.impl.CategoryFactory;
import co.crisi.productopolis.exception.CategoryBusinessException;
import co.crisi.productopolis.exception.CategoryRepeatedException;
import co.crisi.productopolis.model.response.mapper.CategoryMapper;
import co.crisi.productopolis.presenter.register.ICategoryRegisterPresenter;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class CategoryRegisterInteractorTest {

    @Mock
    private ICategoryRegisterPresenter presenter;

    @Mock
    private ICategoryRegisterGateway gateway;

    private final ICategoryFactory factory = new CategoryFactory();

    private final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    private ICategoryRegisterBoundary boundary;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        boundary = new CategoryRegisterInteractor(presenter, gateway, factory, mapper);
    }

    @Test
    void createWhenNonExistingCategory_shouldPrepareSuccessfulView() throws CategoryBusinessException {
        var request = CategoryRequestMother.random();
        var category = factory.create(null, request.name(), request.description(), LocalDate.now());
        var savedCategory = factory.create(1L, category.getName(), category.getDescription(),
                category.getLastUpdated());
        var expectedResponse = mapper.map(savedCategory);
        given(gateway.existsByName(request.name()))
                .willReturn(false);
        given(gateway.save(category))
                .willReturn(savedCategory);

        given(presenter.prepareSuccessfulView(expectedResponse))
                .willReturn(expectedResponse);

        var response = boundary.create(request);

        verify(gateway).existsByName(request.name());
        verify(gateway).save(category);
        verify(presenter).prepareSuccessfulView(expectedResponse);
        assertThat(response)
                .isNotNull()
                .isEqualTo(expectedResponse);

    }

    @Test
    void createWhenExistingCategory_shouldPrepareFailView() throws CategoryBusinessException {
        var request = CategoryRequestMother.random();
        var throwable = new CategoryRepeatedException(String.format("The category %s already exists!", request.name()));
        given(gateway.existsByName(request.name()))
                .willReturn(true);
        given(presenter.prepareFailView(any(CategoryBusinessException.class)))
                .willThrow(throwable);

        var response = catchThrowable(() -> {
            var categoryResponse = boundary.create(request);
        });

        verify(gateway).existsByName(request.name());

        assertThat(response)
                .isNotNull()
                .isEqualTo(throwable);

    }

}