package co.crisi.productopolis.interactors.register;

import co.crisi.productopolis.boundaries.input.register.ICategoryRegisterBoundary;
import co.crisi.productopolis.boundaries.output.ICategoryRegisterGateway;
import co.crisi.productopolis.domain.factory.ICategoryFactory;
import co.crisi.productopolis.exception.CategoryBusinessException;
import co.crisi.productopolis.exception.CategoryRepeatedException;
import co.crisi.productopolis.model.request.CategoryRequest;
import co.crisi.productopolis.model.response.CategoryResponse;
import co.crisi.productopolis.model.response.mapper.CategoryMapper;
import co.crisi.productopolis.presenter.register.ICategoryRegisterPresenter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryRegisterInteractor implements ICategoryRegisterBoundary {

    private final ICategoryRegisterPresenter presenter;

    private final ICategoryRegisterGateway gateway;

    private final ICategoryFactory factory;

    private final CategoryMapper mapper;

    @Override
    public CategoryResponse create(CategoryRequest request) throws CategoryBusinessException {

        if (gateway.existsByName(request.name())) {
            return presenter.prepareFailView(
                    new CategoryRepeatedException(String.format("The category %s already exists!", request.name())));
        }

        var category = factory.create(null, request.name(), request.description());
        var savedCategory = gateway.save(category);

        return presenter.prepareSuccessfulView(mapper.map(savedCategory));
    }

}
