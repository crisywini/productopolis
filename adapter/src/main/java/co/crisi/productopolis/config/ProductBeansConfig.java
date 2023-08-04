package co.crisi.productopolis.config;

import co.crisi.productopolis.boundaries.input.register.IProductRegisterBoundary;
import co.crisi.productopolis.boundaries.output.IAttributeExtractGateway;
import co.crisi.productopolis.boundaries.output.IBrandExtractGateway;
import co.crisi.productopolis.boundaries.output.ICategoryExtractGateway;
import co.crisi.productopolis.boundaries.output.IProductRegisterGateway;
import co.crisi.productopolis.gateway.postgresql.register.ProductRegisterPostgresGateway;
import co.crisi.productopolis.interactors.register.ProductRegisterInteractor;
import co.crisi.productopolis.presenter.register.IProductRegisterPresenter;
import co.crisi.productopolis.presenter.register.ProductRegisterPresenter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductBeansConfig {


    @Bean
    public IProductRegisterPresenter productRegisterPresenter() {
        return new ProductRegisterPresenter();
    }

    @Bean
    public IProductRegisterBoundary productRegisterBoundary(IProductRegisterGateway gateway,
            IProductRegisterPresenter presenter, IBrandExtractGateway brandExtractGateway,
            IAttributeExtractGateway attributeExtractGateway, ICategoryExtractGateway categoryExtractGateway) {
        return new ProductRegisterInteractor(gateway, presenter, brandExtractGateway, attributeExtractGateway,
                categoryExtractGateway);
    }


}
