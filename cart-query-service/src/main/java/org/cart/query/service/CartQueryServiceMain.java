package org.cart.query.service;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.cart.domain.service.repository.CartRepository;
import org.cart.domain.service.repository.ProductRepository;
import org.cart.query.service.CartQueryServiceMain.MyConfiguration;
import org.cart.query.service.service.CartQueryService;
import org.cart.query.service.service.ProductQueryService;
import org.cart.query.service.subscriber.CartQueryEventSubscriber;
import org.cart.query.service.subscriber.ProductQueryEventSubscriber;
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
public class CartQueryServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(CartQueryServiceMain.class, args);
    }

    @Configuration
    @ComponentScan(basePackages = {"org.cart.query.service", "org.cart.domain.service"})
    @EntityScan(basePackages = {"org.cart.query.service", "org.cart.domain.service"})
    @EnableJpaRepositories(basePackages = {"org.cart.domain.service.repository"})
    @EnableEventHandlers
    class MyConfiguration extends WebMvcConfigurerAdapter {

        @Bean
        public CartQueryEventSubscriber cartQueryEventSubscriber(CartQueryService cartQueryService) {
            return new CartQueryEventSubscriber(cartQueryService);
        }

        @Bean
        public ProductQueryEventSubscriber productQueryEventSubscriber(ProductQueryService productQueryService) {
            return new ProductQueryEventSubscriber(productQueryService);
        }

        @Bean
        public CartQueryService cartQueryService(CartRepository cartRepository, ProductQueryService productQueryService) {
            return new CartQueryService(cartRepository, productQueryService);
        }

        @Bean
        public ProductQueryService productQueryService(ProductRepository productRepository) {
            return new ProductQueryService(productRepository);
        }
    }
}
