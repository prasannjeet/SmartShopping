package org.product.query.service;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.product.domain.service.repository.ProductRepository;
import org.product.query.service.ProductQueryServiceMain.MyConfiguration;
import org.product.query.service.service.ProductQueryService;
import org.product.query.service.subscriber.ProductQueryEventSubscriber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Import({MyConfiguration.class, EventuateDriverConfiguration.class})
@EnableAutoConfiguration
public class ProductQueryServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(ProductQueryServiceMain.class, args);
    }

    @Configuration
    @ComponentScan(basePackages = {"org.product.query.service", "org.product.domain.service"})
    @EntityScan(basePackages = {"org.product.query.service", "org.product.domain.service"})
    @EnableJpaRepositories(basePackages = {"org.product.domain.service.repository"})
    @EnableEventHandlers
    class MyConfiguration extends WebMvcConfigurerAdapter {

        @Bean
        public ProductQueryEventSubscriber productQueryEventSubscriber(ProductQueryService productQueryService) {
            return new ProductQueryEventSubscriber(productQueryService);
        }

        @Bean
        public ProductQueryService productQueryService(ProductRepository productRepository) {
            return new ProductQueryService(productRepository);
        }
    }
}