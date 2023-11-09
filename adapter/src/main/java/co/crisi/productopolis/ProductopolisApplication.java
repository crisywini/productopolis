package co.crisi.productopolis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductopolisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductopolisApplication.class, args);
    }

}
