package org.product.query.service;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.product.domain.repository.ProductRepository;
import org.product.query.service.ProductQueryServiceMain.MyConfiguration;
import org.product.query.service.controller.Controller;
import org.product.query.service.service.QueryService;
import org.product.query.service.subscriber.QueryEventSubscriber;
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
    @ComponentScan(basePackages = {"org.product.query.service", "org.product.domain", "org.utils"})
    @EntityScan(basePackages = {"org.product.query.service", "org.product.domain", "org.utils"})
    @EnableJpaRepositories(basePackages = {"org.product.domain.repository"})
    @EnableEventHandlers
    class MyConfiguration extends WebMvcConfigurerAdapter {

        @Bean
        public QueryEventSubscriber queryEventSubscriber(ProductRepository productRepository) {
            return new QueryEventSubscriber(productRepository);
        }

        @Bean
        public QueryService queryService(ProductRepository productRepository) {
            return new QueryService(productRepository);
        }

        @Bean
        public Controller controller(QueryService queryService) {
            return new Controller(queryService);
        }
    }
}
