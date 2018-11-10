package org.store.command.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.store.command.service.StoreCommandServiceMain.MyConfiguration;
import org.store.command.service.aggregate.StoreAggregate;
import org.store.command.service.command.StoreCommand;
import org.store.command.service.subscriber.CommandEventSubscriber;
import org.store.domain.repository.PriceTagRepository;
import org.store.domain.repository.StoreRepository;

@Configuration
@Import({MyConfiguration.class, EventuateDriverConfiguration.class})
@EnableAutoConfiguration
public class StoreCommandServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(StoreCommandServiceMain.class, args);
    }

    @Configuration
    @ComponentScan(basePackages = {"org.store.command.service", "org.store.domain", "org.utils"})
    @EntityScan(basePackages = {"org.store.command.service", "org.store.domain", "org.utils"})
    @EnableJpaRepositories(basePackages = {"org.store.domain.repository"})
    @EnableEventHandlers
    class MyConfiguration extends WebMvcConfigurerAdapter {

        @Bean
        public AggregateRepository<StoreAggregate, StoreCommand> aggregateRepository(
                EventuateAggregateStore eventuateAggregateStore) {
            return new AggregateRepository<>(StoreAggregate.class, eventuateAggregateStore);
        }

        @Bean
        public CommandEventSubscriber commandEventSubscriber(AggregateRepository<StoreAggregate, StoreCommand> aggregateRepository,
                                                             StoreRepository storeRepository, PriceTagRepository priceTagRepository) {
            return new CommandEventSubscriber(aggregateRepository, priceTagRepository, storeRepository);
        }
    }
}