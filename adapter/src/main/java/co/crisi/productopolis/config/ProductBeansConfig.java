package co.crisi.productopolis.config;

import co.crisi.productopolis.boundaries.input.delete.IProductDeleteBoundary;
import co.crisi.productopolis.boundaries.input.extract.IProductExtractBoundary;
import co.crisi.productopolis.boundaries.input.register.IProductRegisterBoundary;
import co.crisi.productopolis.boundaries.input.update.IProductUpdateBoundary;
import co.crisi.productopolis.boundaries.output.IAttributeExtractGateway;
import co.crisi.productopolis.boundaries.output.IBrandExtractGateway;
import co.crisi.productopolis.boundaries.output.ICategoryExtractGateway;
import co.crisi.productopolis.boundaries.output.IProductDeleteGateway;
import co.crisi.productopolis.boundaries.output.IProductExtractGateway;
import co.crisi.productopolis.boundaries.output.IProductRegisterGateway;
import co.crisi.productopolis.boundaries.output.IProductUpdateGateway;
import co.crisi.productopolis.interactors.delete.ProductDeleteInteractor;
import co.crisi.productopolis.interactors.extract.ProductExtractInteractor;
import co.crisi.productopolis.interactors.register.ProductRegisterInteractor;
import co.crisi.productopolis.interactors.update.ProductUpdateInteractor;
import co.crisi.productopolis.presenter.delete.IProductDeletePresenter;
import co.crisi.productopolis.presenter.delete.ProductDeletePresenter;
import co.crisi.productopolis.presenter.extract.IProductExtractPresenter;
import co.crisi.productopolis.presenter.extract.ProductExtractPresenter;
import co.crisi.productopolis.presenter.register.IProductRegisterPresenter;
import co.crisi.productopolis.presenter.register.ProductRegisterPresenter;
import co.crisi.productopolis.presenter.update.IProductUpdatePresenter;
import co.crisi.productopolis.presenter.update.ProductUpdatePresenter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductBeansConfig {


    @Bean
    public IProductRegisterPresenter productRegisterPresenter() {
        return new ProductRegisterPresenter();
    }

    @Bean
    public IProductExtractPresenter productExtractPresenter() {
        return new ProductExtractPresenter();
    }

    @Bean
    public IProductUpdatePresenter productUpdatePresenter() {
        return new ProductUpdatePresenter();
    }

    @Bean
    public IProductDeletePresenter productDeletePresenter() {
        return new ProductDeletePresenter();
    }

    @Bean
    public IProductRegisterBoundary productRegisterBoundary(IProductRegisterGateway gateway,
            IProductRegisterPresenter presenter, IBrandExtractGateway brandExtractGateway,
            IAttributeExtractGateway attributeExtractGateway, ICategoryExtractGateway categoryExtractGateway) {
        return new ProductRegisterInteractor(gateway, presenter, brandExtractGateway, attributeExtractGateway,
                categoryExtractGateway);
    }

    @Bean
    public IProductExtractBoundary productExtractBoundary(IProductExtractGateway gateway,
            IProductExtractPresenter presenter) {
        return new ProductExtractInteractor(gateway, presenter);
    }

    @Bean
    public IProductDeleteBoundary productDeleteBoundary(IProductDeleteGateway gateway,
            IProductDeletePresenter presenter) {
        return new ProductDeleteInteractor(gateway, presenter);
    }

    @Bean
    public IProductUpdateBoundary productUpdateBoundary(IProductUpdateGateway gateway,
            IProductUpdatePresenter presenter, IBrandExtractGateway brandExtractGateway,
            IProductExtractGateway productExtractGateway, IAttributeExtractGateway attributeExtractGateway,
            ICategoryExtractGateway categoryExtractGateway) {
        return new ProductUpdateInteractor(gateway, presenter, productExtractGateway, brandExtractGateway,
                attributeExtractGateway, categoryExtractGateway);
    }


}
