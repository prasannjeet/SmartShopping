package org.cart.command.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.cart.command.service.CartCommandServiceMain.MyConfiguration;
import org.cart.command.service.aggregate.CartAggregate;
import org.cart.command.service.aggregate.CartBulkDeleteAggregate;
import org.cart.command.service.aggregate.ProductAggregate;
import org.cart.command.service.aggregate.ProductBulkDeleteAggregate;
import org.cart.command.service.command.CartCommand;
import org.cart.command.service.command.ProductCommand;
import org.cart.command.service.service.CartCommandService;
import org.cart.command.service.service.CartQueryService;
import org.cart.command.service.service.ProductCommandService;
import org.cart.command.service.service.ProductQueryService;
import org.cart.command.service.subscriber.CartCommandEventSubscriber;
import org.cart.command.service.subscriber.ProductCommandEventSubscriber;
import org.cart.domain.service.repository.CartRepository;
import org.cart.domain.service.repository.ProductRepository;
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
public class CartCommandServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(CartCommandServiceMain.class, args);
    }

    @Configuration
    @ComponentScan(basePackages = {"org.cart.command.service", "org.cart.domain.service"})
    @EntityScan(basePackages = {"org.cart.command.service", "org.cart.domain.service"})
    @EnableJpaRepositories(basePackages = {"org.cart.domain.service.repository"})
    @EnableEventHandlers
    class MyConfiguration extends WebMvcConfigurerAdapter {

        @Bean
        public AggregateRepository<CartAggregate, CartCommand>
        cartAggregateRepository(EventuateAggregateStore eventuateAggregateStore) {
            return new AggregateRepository<>(CartAggregate.class, eventuateAggregateStore);
        }

        @Bean
        public AggregateRepository<ProductAggregate, ProductCommand>
        productAggregateRepository(EventuateAggregateStore eventuateAggregateStore) {
            return new AggregateRepository<>(ProductAggregate.class, eventuateAggregateStore);
        }

        @Bean
        public AggregateRepository<CartBulkDeleteAggregate, CartCommand>
        cartBulkDeleteAggregateRepository(EventuateAggregateStore eventuateAggregateStore) {
            return new AggregateRepository<>(CartBulkDeleteAggregate.class, eventuateAggregateStore);
        }

        @Bean
        public AggregateRepository<ProductBulkDeleteAggregate, ProductCommand>
        productBulkDeleteAggregateRepository(EventuateAggregateStore eventuateAggregateStore) {
            return new AggregateRepository<>(ProductBulkDeleteAggregate.class, eventuateAggregateStore);
        }

        @Bean
        public CartCommandService cartCommandService(AggregateRepository<CartAggregate, CartCommand> aggregateRepository,
                                                     AggregateRepository<CartBulkDeleteAggregate, CartCommand> bulkDeleteAggregateRepository) {
            return new CartCommandService(aggregateRepository, bulkDeleteAggregateRepository);
        }

        @Bean
        public ProductCommandService productCommandService(AggregateRepository<ProductAggregate, ProductCommand> aggregateRepository,
                                                           AggregateRepository<ProductBulkDeleteAggregate, ProductCommand> bulkDeleteAggregateRepository) {
            return new ProductCommandService(aggregateRepository, bulkDeleteAggregateRepository);
        }

        @Bean
        public CartQueryService cartQueryService(CartRepository cartRepository) {
            return new CartQueryService(cartRepository);
        }

        @Bean
        public ProductQueryService productQueryService(ProductRepository productRepository) {
            return new ProductQueryService(productRepository);
        }

        @Bean
        public CartCommandEventSubscriber cartCommandEventSubscriber() {
            return new CartCommandEventSubscriber();
        }

        @Bean
        public ProductCommandEventSubscriber productCommandEventSubscriber() {
            return new ProductCommandEventSubscriber();
        }
    }
}
