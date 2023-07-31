package co.crisi.productopolis.interactors.update;

import co.crisi.productopolis.boundaries.input.update.ICategoryUpdateBoundary;
import co.crisi.productopolis.boundaries.output.ICategoryUpdateGateway;
import co.crisi.productopolis.domain.CategoryRequestMother;
import co.crisi.productopolis.domain.factory.ICategoryFactory;
import co.crisi.productopolis.domain.factory.impl.CategoryFactory;
import co.crisi.productopolis.exception.CategoryBusinessException;
import co.crisi.productopolis.exception.CategoryNotFoundException;
import co.crisi.productopolis.model.request.CategoryUpdateRequest;
import co.crisi.productopolis.model.response.mapper.CategoryMapper;
import co.crisi.productopolis.presenter.update.ICategoryUpdatePresenter;
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

class CategoryUpdateInteractorTest {

    @Mock
    private ICategoryUpdateGateway gateway;

    @Mock
    private ICategoryUpdatePresenter presenter;

    private final ICategoryFactory factory = new CategoryFactory();

    private final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    private ICategoryUpdateBoundary boundary;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        boundary = new CategoryUpdateInteractor(presenter, gateway, factory);
    }

    @Test
    void updateWhenExistingCategory_shouldPrepareSuccessfulView() throws CategoryBusinessException {
        var id = 1L;
        var newInfo = CategoryRequestMother.random();
        var request = new CategoryUpdateRequest(id, newInfo);
        var categoryToUpdate = factory.create(request.id(), request.newInfo().name(), request.newInfo().description());
        var categoryUpdated = factory.create(categoryToUpdate.getId(), categoryToUpdate.getName(),
                categoryToUpdate.getDescription());
        var category = mapper.map(categoryUpdated);
        given(gateway.existsById(id))
                .willReturn(true);
        given(gateway.update(id, categoryToUpdate))
                .willReturn(categoryUpdated);

        given(presenter.prepareSuccessfulView(category))
                .willReturn(category);

        var response = boundary.update(request);

        verify(gateway).existsById(id);
        verify(gateway).update(id, categoryToUpdate);
        verify(presenter).prepareSuccessfulView(category);
        assertThat(response)
                .isEqualTo(category);

    }


    @Test
    void updateWhenNonExistingCategory_shouldPrepareFailView() throws CategoryBusinessException {
        var id = 1L;
        var newInfo = CategoryRequestMother.random();
        var request = new CategoryUpdateRequest(id, newInfo);
        var throwable = new CategoryNotFoundException(
                String.format("The category with id %d does not exists!", request.id()));

        given(gateway.existsById(id))
                .willReturn(false);
        given(presenter.prepareFailView(any(CategoryBusinessException.class)))
                .willThrow(throwable);

        var response = catchThrowable(() -> {
            var categoryResponse = boundary.update(request);
        });

        verify(gateway).existsById(id);
        verify(presenter).prepareFailView(any(CategoryBusinessException.class));
        assertThat(response)
                .isEqualTo(throwable);

    }

}