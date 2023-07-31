package co.crisi.productopolis.interactors.update;

import co.crisi.productopolis.boundaries.input.update.ICategoryUpdateBoundary;
import co.crisi.productopolis.boundaries.output.ICategoryUpdateGateway;
import co.crisi.productopolis.domain.factory.ICategoryFactory;
import co.crisi.productopolis.exception.CategoryBusinessException;
import co.crisi.productopolis.exception.CategoryNotFoundException;
import co.crisi.productopolis.model.request.CategoryUpdateRequest;
import co.crisi.productopolis.model.response.CategoryResponse;
import co.crisi.productopolis.model.response.mapper.CategoryMapper;
import co.crisi.productopolis.presenter.update.ICategoryUpdatePresenter;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;

@RequiredArgsConstructor
public class CategoryUpdateInteractor implements ICategoryUpdateBoundary {

    private final ICategoryUpdatePresenter presenter;

    private final ICategoryUpdateGateway gateway;

    private final ICategoryFactory factory;

    private final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    @Override
    public CategoryResponse update(CategoryUpdateRequest request) throws CategoryBusinessException {

        if (!gateway.existsById(request.id())) {
            return presenter.prepareFailView(new CategoryNotFoundException(
                    String.format("The category with id %d does not exists!", request.id())));
        }

        var categoryToUpdate = factory.create(request.id(), request.newInfo().name(), request.newInfo().description());

        var categoryUpdated = gateway.update(request.id(), categoryToUpdate);

        return presenter.prepareSuccessfulView(mapper.map(categoryUpdated));
    }

}
