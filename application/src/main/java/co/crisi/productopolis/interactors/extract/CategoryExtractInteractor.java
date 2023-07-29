package co.crisi.productopolis.interactors.extract;

import co.crisi.productopolis.boundaries.input.extract.ICategoryExtractBoundary;
import co.crisi.productopolis.boundaries.output.ICategoryExtractGateway;
import co.crisi.productopolis.exception.CategoryBusinessException;
import co.crisi.productopolis.exception.CategoryNotFoundException;
import co.crisi.productopolis.model.response.CategoryResponse;
import co.crisi.productopolis.model.response.mapper.CategoryMapper;
import co.crisi.productopolis.presenter.extract.ICategoryExtractPresenter;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryExtractInteractor implements ICategoryExtractBoundary {

    private final ICategoryExtractGateway gateway;

    private final ICategoryExtractPresenter presenter;

    private final CategoryMapper mapper;

    @Override
    public CategoryResponse getById(Long id) throws CategoryBusinessException {
        if (!gateway.existsById(id)) {
            return presenter.prepareFailView(
                    new CategoryNotFoundException(String.format("The category with id %d was not found!", id)));
        }

        var category = gateway.getById(id);
        return presenter.prepareSuccessfulView(mapper.map(category));
    }

    @Override
    public List<CategoryResponse> getAll() {
        return presenter.prepareSuccessfulView(mapper.map(gateway.getAll()));
    }

}
