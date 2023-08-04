package co.crisi.productopolis.config;

import co.crisi.productopolis.boundaries.input.extract.IBrandExtractBoundary;
import co.crisi.productopolis.boundaries.input.register.IBrandRegisterBoundary;
import co.crisi.productopolis.boundaries.input.update.IBrandUpdateBoundary;
import co.crisi.productopolis.boundaries.output.IBrandExtractGateway;
import co.crisi.productopolis.boundaries.output.IBrandRegisterGateway;
import co.crisi.productopolis.boundaries.output.IBrandUpdateGateway;
import co.crisi.productopolis.domain.factory.IBrandFactory;
import co.crisi.productopolis.domain.factory.impl.BrandFactory;
import co.crisi.productopolis.interactors.extract.BrandExtractInteractor;
import co.crisi.productopolis.interactors.register.BrandRegisterInteractor;
import co.crisi.productopolis.interactors.update.BrandUpdateInteractor;
import co.crisi.productopolis.model.response.mapper.BrandMapper;
import co.crisi.productopolis.presenter.delete.BrandDeletePresenter;
import co.crisi.productopolis.presenter.delete.IBrandDeletePresenter;
import co.crisi.productopolis.presenter.extract.BrandExtractPresenter;
import co.crisi.productopolis.presenter.extract.IBrandExtractPresenter;
import co.crisi.productopolis.presenter.register.BrandRegisterPresenter;
import co.crisi.productopolis.presenter.register.IBrandRegisterPresenter;
import co.crisi.productopolis.presenter.update.BrandUpdatePresenter;
import co.crisi.productopolis.presenter.update.IBrandUpdatePresenter;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrandBeansConfig {

    private final BrandMapper MAPPER = Mappers.getMapper(BrandMapper.class);

    private final IBrandFactory FACTORY = new BrandFactory();

    @Bean
    public IBrandRegisterPresenter brandRegisterPresenter() {
        return new BrandRegisterPresenter();
    }

    @Bean
    public IBrandExtractPresenter brandExtractPresenter() {
        return new BrandExtractPresenter();
    }

    @Bean
    public IBrandDeletePresenter brandDeletePresenter() {
        return new BrandDeletePresenter();
    }

    @Bean
    public IBrandUpdatePresenter brandUpdatePresenter() {
        return new BrandUpdatePresenter();
    }

    @Bean
    public IBrandRegisterBoundary brandRegisterBoundary(IBrandRegisterPresenter presenter,
            IBrandRegisterGateway gateway) {
        return new BrandRegisterInteractor(presenter, FACTORY, gateway,
                MAPPER);
    }

    @Bean
    public IBrandExtractBoundary brandExtractBoundary(IBrandExtractPresenter presenter, IBrandExtractGateway gateway) {
        return new BrandExtractInteractor(MAPPER, presenter, gateway);
    }

    @Bean
    public IBrandUpdateBoundary brandUpdateBoundary(IBrandUpdateGateway gateway, IBrandUpdatePresenter presenter) {
        return new BrandUpdateInteractor(FACTORY, gateway, presenter, MAPPER);
    }


}
