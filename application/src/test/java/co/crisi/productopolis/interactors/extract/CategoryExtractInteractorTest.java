package co.crisi.productopolis.interactors.extract;

import co.crisi.productopolis.boundaries.input.extract.ICategoryExtractBoundary;
import co.crisi.productopolis.boundaries.output.ICategoryExtractGateway;
import co.crisi.productopolis.domain.factory.ICategoryFactory;
import co.crisi.productopolis.domain.factory.impl.CategoryFactory;
import co.crisi.productopolis.exception.CategoryBusinessException;
import co.crisi.productopolis.exception.CategoryNotFoundException;
import co.crisi.productopolis.model.response.mapper.CategoryMapper;
import co.crisi.productopolis.presenter.extract.ICategoryExtractPresenter;
import java.util.List;
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

class CategoryExtractInteractorTest {

    @Mock
    private ICategoryExtractGateway gateway;

    @Mock
    private ICategoryExtractPresenter presenter;

    private final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    private ICategoryExtractBoundary boundary;

    private final ICategoryFactory factory = new CategoryFactory();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        boundary = new CategoryExtractInteractor(gateway, presenter, mapper);
    }

    @Test
    void getByIdWhenExisting_shouldPrepareSuccessfulView() throws CategoryBusinessException {
        var id = 1L;
        var category = factory.create(id, "computers", "a computer or system computer based like");
        var expectedResponse = mapper.map(category);
        given(gateway.existsById(id))
                .willReturn(true);
        given(gateway.getById(id))
                .willReturn(category);

        given(presenter.prepareSuccessfulView(expectedResponse))
                .willReturn(expectedResponse);

        var response = boundary.getById(id);

        verify(gateway).existsById(id);
        verify(gateway).getById(id);
        verify(presenter).prepareSuccessfulView(expectedResponse);
        assertThat(response)
                .isNotNull()
                .isEqualTo(expectedResponse);
    }

    @Test
    void getByIdWhenNonExisting_shouldPrepareFailView() throws CategoryBusinessException {
        var id = 1L;
        var throwable = new CategoryNotFoundException(String.format("The category with id %d was not found!", id));
        given(gateway.existsById(id))
                .willReturn(false);
        given(presenter.prepareFailView(any(CategoryBusinessException.class)))
                .willThrow(throwable);

        var response = catchThrowable(() -> {
            var categoryResponse = boundary.getById(id);
        });

        verify(gateway).existsById(id);
        verify(presenter).prepareFailView(any(CategoryBusinessException.class));
        assertThat(response)
                .isNotNull()
                .isEqualTo(throwable);
    }

    @Test
    void getAll_shouldPrepareSuccessfulView() throws CategoryBusinessException {
        var categoryOne = factory.create(1L, "computers", "a computer or system computer based like");
        var categoryTwo = factory.create(2L, "tablet", "a tablet or system tablet based like");
        var categories = List.of(categoryOne, categoryTwo);

        var expectedResponse = mapper.map(categories);
        given(gateway.getAll())
                .willReturn(categories);

        given(presenter.prepareSuccessfulView(expectedResponse))
                .willReturn(expectedResponse);

        var response = boundary.getAll();

        verify(gateway).getAll();
        verify(presenter).prepareSuccessfulView(expectedResponse);
        assertThat(response)
                .isNotNull()
                .isEqualTo(expectedResponse);
    }

}