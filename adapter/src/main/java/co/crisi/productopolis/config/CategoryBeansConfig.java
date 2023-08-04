package co.crisi.productopolis.config;

import co.crisi.productopolis.boundaries.input.delete.ICategoryDeleteBoundary;
import co.crisi.productopolis.boundaries.input.extract.ICategoryExtractBoundary;
import co.crisi.productopolis.boundaries.input.register.ICategoryRegisterBoundary;
import co.crisi.productopolis.boundaries.input.update.ICategoryUpdateBoundary;
import co.crisi.productopolis.boundaries.output.ICategoryDeleteGateway;
import co.crisi.productopolis.boundaries.output.ICategoryExtractGateway;
import co.crisi.productopolis.boundaries.output.ICategoryRegisterGateway;
import co.crisi.productopolis.boundaries.output.ICategoryUpdateGateway;
import co.crisi.productopolis.domain.factory.ICategoryFactory;
import co.crisi.productopolis.domain.factory.impl.CategoryFactory;
import co.crisi.productopolis.interactors.delete.CategoryDeleteInteractor;
import co.crisi.productopolis.interactors.extract.CategoryExtractInteractor;
import co.crisi.productopolis.interactors.register.CategoryRegisterInteractor;
import co.crisi.productopolis.interactors.update.CategoryUpdateInteractor;
import co.crisi.productopolis.model.response.mapper.CategoryMapper;
import co.crisi.productopolis.presenter.delete.CategoryDeletePresenter;
import co.crisi.productopolis.presenter.delete.ICategoryDeletePresenter;
import co.crisi.productopolis.presenter.extract.CategoryExtractPresenter;
import co.crisi.productopolis.presenter.extract.ICategoryExtractPresenter;
import co.crisi.productopolis.presenter.register.CategoryRegisterPresenter;
import co.crisi.productopolis.presenter.register.ICategoryRegisterPresenter;
import co.crisi.productopolis.presenter.update.CategoryUpdatePresenter;
import co.crisi.productopolis.presenter.update.ICategoryUpdatePresenter;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryBeansConfig {

    private final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    private final ICategoryFactory factory = new CategoryFactory();


    @Bean
    public ICategoryRegisterPresenter categoryRegisterPresenter() {
        return new CategoryRegisterPresenter();
    }

    @Bean
    public ICategoryExtractPresenter categoryExtractPresenter() {
        return new CategoryExtractPresenter();
    }

    @Bean
    public ICategoryDeletePresenter categoryDeletePresenter() {
        return new CategoryDeletePresenter();
    }

    @Bean
    public ICategoryUpdatePresenter categoryUpdatePresenter() {
        return new CategoryUpdatePresenter();
    }

    @Bean
    public ICategoryRegisterBoundary categoryRegisterBoundary(ICategoryRegisterPresenter presenter,
            ICategoryRegisterGateway gateway) {
        return new CategoryRegisterInteractor(presenter, gateway, factory, mapper);
    }

    @Bean
    public ICategoryUpdateBoundary categoryUpdateBoundary(ICategoryUpdatePresenter presenter,
            ICategoryUpdateGateway gateway) {
        return new CategoryUpdateInteractor(presenter, gateway, factory);
    }

    @Bean
    public ICategoryDeleteBoundary categoryDeleteBoundary(ICategoryDeleteGateway gateway,
            ICategoryDeletePresenter presenter) {
        return new CategoryDeleteInteractor(gateway, presenter);
    }

    @Bean
    public ICategoryExtractBoundary categoryExtractBoundary(ICategoryExtractGateway gateway,
            ICategoryExtractPresenter presenter) {
        return new CategoryExtractInteractor(gateway, presenter, mapper);
    }

}
