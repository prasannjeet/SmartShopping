package org.cart.command.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.cart.command.service.CartCommandServiceMain.MyConfiguration;
import org.cart.command.service.aggregate.CartAggregate;
import org.cart.command.service.command.CartCommand;
import org.cart.command.service.controller.Controller;
import org.cart.command.service.service.CommandService;
import org.cart.command.service.subscriber.CommandEventSubscriber;
import org.cart.domain.repository.CartRepository;
import org.cart.domain.repository.ProductRepository;
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
    @ComponentScan(basePackages = {"org.cart.command.service", "org.cart.domain", "org.utils"})
    @EntityScan(basePackages = {"org.cart.command.service", "org.cart.domain", "org.utils"})
    @EnableJpaRepositories(basePackages = {"org.cart.domain.repository"})
    @EnableEventHandlers
    class MyConfiguration extends WebMvcConfigurerAdapter {

        @Bean
        public AggregateRepository<CartAggregate, CartCommand> aggregateRepository(
                EventuateAggregateStore eventuateAggregateStore) {
            return new AggregateRepository<>(CartAggregate.class, eventuateAggregateStore);
        }

        @Bean
        public CommandService commandService(AggregateRepository<CartAggregate, CartCommand> aggregateRepository,
                                             ProductRepository productRepository, CartRepository cartRepository,
                                             CommandEventSubscriber commandEventSubscriber) {
            return new CommandService(aggregateRepository, productRepository, cartRepository, commandEventSubscriber);
        }

        @Bean
        public Controller controller(CommandService commandService) {
            return new Controller(commandService);
        }

        @Bean
        public CommandEventSubscriber commandEventSubscriber() {
            return new CommandEventSubscriber();
        }
    }
}
