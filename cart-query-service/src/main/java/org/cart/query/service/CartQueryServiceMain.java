package org.cart.query.service;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.cart.domain.repository.CartRepository;
import org.cart.domain.repository.ProductRepository;
import org.cart.query.service.CartQueryServiceMain.MyConfiguration;
import org.cart.query.service.controller.Controller;
import org.cart.query.service.service.QueryService;
import org.cart.query.service.subscriber.QueryEventSubscriber;
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
    @ComponentScan(basePackages = {"org.cart.query.service", "org.cart.domain", "org.utils"})
    @EntityScan(basePackages = {"org.cart.query.service", "org.cart.domain", "org.utils"})
    @EnableJpaRepositories(basePackages = {"org.cart.domain.repository"})
    @EnableEventHandlers
    class MyConfiguration extends WebMvcConfigurerAdapter {

        @Bean
        public QueryEventSubscriber queryEventSubscriber(CartRepository cartRepository, ProductRepository productRepository) {
            return new QueryEventSubscriber(cartRepository, productRepository);
        }

        @Bean
        public QueryService queryService(CartRepository cartRepository, ProductRepository productRepository) {
            return new QueryService(cartRepository, productRepository);
        }

        @Bean
        public Controller controller(QueryService queryService) {
            return new Controller(queryService);
        }
    }
}
