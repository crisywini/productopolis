package co.crisi.productopolis.config;

import co.crisi.productopolis.presenter.register.BrandRegisterPresenter;
import co.crisi.productopolis.presenter.register.IBrandRegisterPresenter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrandBeansConfig {

    @Bean
    public IBrandRegisterPresenter brandRegisterPresenter(){
        return new BrandRegisterPresenter();
    }


}
