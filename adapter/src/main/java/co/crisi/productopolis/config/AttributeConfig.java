package co.crisi.productopolis.config;

import co.crisi.productopolis.boundaries.input.register.IAttributeRegisterBoundary;
import co.crisi.productopolis.boundaries.output.IAttributeRegisterGateway;
import co.crisi.productopolis.domain.factory.IAttributeFactory;
import co.crisi.productopolis.domain.factory.impl.AttributeFactory;
import co.crisi.productopolis.gateways.model.mapper.AttributeMapper;
import co.crisi.productopolis.gateways.postgresql.AttributeRegisterPostgresSQLGateway;
import co.crisi.productopolis.interactors.register.AttributeRegisterInteractor;
import co.crisi.productopolis.presenter.register.AttributeRegisterPresenter;
import co.crisi.productopolis.presenter.register.IAttributeRegisterPresenter;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AttributeConfig {


    @Bean
    public IAttributeRegisterPresenter attributeRegisterPresenter() {
        return new AttributeRegisterPresenter();
    }

    @Bean
    public IAttributeFactory attributeFactory() {
        return new AttributeFactory();
    }

    @Bean
    public IAttributeRegisterGateway attributeRegisterGateway() {
        return new AttributeRegisterPostgresSQLGateway();
    }

    @Bean
    public IAttributeRegisterBoundary attributeRegisterBoundary() {
        return new AttributeRegisterInteractor(attributeFactory(), attributeRegisterPresenter(),
                attributeRegisterGateway());
    }


}
