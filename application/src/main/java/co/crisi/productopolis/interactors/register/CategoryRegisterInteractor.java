package co.crisi.productopolis.interactors.register;

import co.crisi.productopolis.boundaries.input.register.ICategoryRegisterBoundary;
import co.crisi.productopolis.boundaries.output.ICategoryRegisterGateway;
import co.crisi.productopolis.domain.factory.ICategoryFactory;
import co.crisi.productopolis.domain.factory.impl.CategoryFactory;
import co.crisi.productopolis.exception.CategoryBusinessException;
import co.crisi.productopolis.model.request.CategoryCreationRequest;
import co.crisi.productopolis.model.response.CategoryResponse;
import co.crisi.productopolis.presenter.register.ICategoryRegisterPresenter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryRegisterInteractor implements ICategoryRegisterBoundary {

    private final ICategoryRegisterGateway gateway;

    private final ICategoryRegisterPresenter presenter;

    private final ICategoryFactory factory = new CategoryFactory();

    @Override
    public CategoryResponse create(CategoryCreationRequest request) throws CategoryBusinessException {
        return null;
    }

}
