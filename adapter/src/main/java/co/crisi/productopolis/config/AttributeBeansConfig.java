package co.crisi.productopolis.config;

import co.crisi.productopolis.boundaries.input.delete.IAttributeDeleteBoundary;
import co.crisi.productopolis.boundaries.input.extract.IAttributeExtractBoundary;
import co.crisi.productopolis.boundaries.input.register.IAttributeRegisterBoundary;
import co.crisi.productopolis.boundaries.input.update.IAttributeUpdateBoundary;
import co.crisi.productopolis.boundaries.output.IAttributeDeleteGateway;
import co.crisi.productopolis.boundaries.output.IAttributeExtractGateway;
import co.crisi.productopolis.boundaries.output.IAttributeRegisterGateway;
import co.crisi.productopolis.boundaries.output.IAttributeUpdateGateway;
import co.crisi.productopolis.domain.factory.IAttributeFactory;
import co.crisi.productopolis.domain.factory.impl.AttributeFactory;
import co.crisi.productopolis.interactors.delete.AttributeDeleteInteractor;
import co.crisi.productopolis.interactors.extract.AttributeExtractInteractor;
import co.crisi.productopolis.interactors.register.AttributeRegisterInteractor;
import co.crisi.productopolis.interactors.update.AttributeUpdateInteractor;
import co.crisi.productopolis.model.response.mapper.AttributeMapper;
import co.crisi.productopolis.presenter.delete.AttributeDeletePresenter;
import co.crisi.productopolis.presenter.delete.IAttributeDeletePresenter;
import co.crisi.productopolis.presenter.extract.AttributeExtractPresenter;
import co.crisi.productopolis.presenter.extract.IAttributeExtractPresenter;
import co.crisi.productopolis.presenter.register.AttributeRegisterPresenter;
import co.crisi.productopolis.presenter.register.IAttributeRegisterPresenter;
import co.crisi.productopolis.presenter.update.AttributeUpdatePresenter;
import co.crisi.productopolis.presenter.update.IAttributeUpdatePresenter;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AttributeBeansConfig {

    private final IAttributeFactory factory = new AttributeFactory();

    private final AttributeMapper mapper = Mappers.getMapper(AttributeMapper.class);

    @Bean
    public IAttributeRegisterPresenter attributeRegisterPresenter() {
        return new AttributeRegisterPresenter();
    }

    @Bean
    public IAttributeExtractPresenter attributeExtractPresenter() {
        return new AttributeExtractPresenter();
    }

    @Bean
    public IAttributeDeletePresenter attributeDeletePresenter() {
        return new AttributeDeletePresenter();
    }

    @Bean
    public IAttributeUpdatePresenter attributeUpdatePresenter() {
        return new AttributeUpdatePresenter();
    }

    @Bean
    public IAttributeRegisterBoundary attributeRegisterBoundary(IAttributeRegisterPresenter presenter,
            IAttributeRegisterGateway gateway) {
        return new AttributeRegisterInteractor(factory, presenter, gateway, mapper);
    }

    @Bean
    public IAttributeExtractBoundary attributeExtractBoundary(IAttributeExtractPresenter presenter,
            IAttributeExtractGateway gateway) {
        return new AttributeExtractInteractor(presenter, gateway, mapper);
    }

    @Bean
    public IAttributeDeleteBoundary attributeDeleteBoundary(IAttributeDeletePresenter presenter,
            IAttributeDeleteGateway gateway) {
        return new AttributeDeleteInteractor(presenter, gateway);
    }

    @Bean
    public IAttributeUpdateBoundary attributeUpdateBoundary(IAttributeUpdateGateway gateway,
            IAttributeUpdatePresenter presenter) {
        return new AttributeUpdateInteractor(gateway, presenter, mapper);
    }

}
