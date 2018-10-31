package org.gateway.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.cart.command.service.CartCommandServiceMain.MyConfiguration;
import org.cart.command.service.aggregate.ProductAggregate;
import org.cart.command.service.command.ProductCommand;
import org.cart.command.service.service.CommandService;
import org.cart.command.service.service.QueryService;
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
public class GatewayServiceMain {

    public static void main(String[] args) {
        SpringApplication.run( GatewayServiceMain.class, args);
    }

    @Configuration
    @EnableEventHandlers
    class MyConfiguration extends WebMvcConfigurerAdapter {
/*
        @Bean
        public AggregateRepository<ProductAggregate, ProductCommand> aggregateRepository(
                EventuateAggregateStore eventuateAggregateStore) {
            return new AggregateRepository<>(ProductAggregate.class, eventuateAggregateStore);
        }

        @Bean
        public CommandService commandService(AggregateRepository<ProductAggregate, ProductCommand> aggregateRepository,
                                             QueryService queryService) {
            return new CommandService(aggregateRepository, queryService);
        }

        @Bean
        public QueryService queryService(CartRepository cartRepository, ProductRepository productRepository) {
            return new QueryService(cartRepository, productRepository);
        }
*/
    }
}
