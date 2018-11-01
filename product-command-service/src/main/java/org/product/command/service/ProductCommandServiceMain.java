package org.product.command.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.product.command.service.ProductCommandServiceMain.MyConfiguration;
import org.product.command.service.aggregate.ProductAggregate;
import org.product.command.service.aggregate.ProductBulkDeleteAggregate;
import org.product.command.service.command.ProductCommand;
import org.product.command.service.service.ProductCommandService;
import org.product.command.service.service.ProductQueryService;
import org.product.command.service.subscriber.ProductCommandEventSubscriber;
import org.product.domain.service.repository.ProductRepository;
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
public class ProductCommandServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(ProductCommandServiceMain.class, args);
    }

    @Configuration
    @ComponentScan(basePackages = {"org.product.command.service", "org.product.domain.service"})
    @EntityScan(basePackages = {"org.product.command.service", "org.product.domain.service"})
    @EnableJpaRepositories(basePackages = {"org.product.domain.service.repository"})
    @EnableEventHandlers
    class MyConfiguration extends WebMvcConfigurerAdapter {

        @Bean
        public AggregateRepository<ProductAggregate, ProductCommand> productAggregateRepository(
        		EventuateAggregateStore eventuateAggregateStore) {
            return new AggregateRepository<>(ProductAggregate.class, eventuateAggregateStore);
        }

        @Bean
        public AggregateRepository<ProductBulkDeleteAggregate, ProductCommand> productBulkDeleteAggregateRepository(
        			EventuateAggregateStore eventuateAggregateStore) {
        		return new AggregateRepository<>(ProductBulkDeleteAggregate.class, eventuateAggregateStore);
        }

        @Bean
        public ProductCommandService productCommandService(
                AggregateRepository<ProductAggregate, ProductCommand> productAggregateRepository,
                AggregateRepository<ProductBulkDeleteAggregate, ProductCommand> productBulkDeleteAggregateRepository) {
            return new ProductCommandService(productAggregateRepository, productBulkDeleteAggregateRepository);
        }

        @Bean
        public ProductQueryService productQueryService(ProductRepository productRepository) {
            return new ProductQueryService(productRepository);
        }

        @Bean
        public ProductCommandEventSubscriber productCommandEventSubscriber() {
            return new ProductCommandEventSubscriber();
        }
    }
}