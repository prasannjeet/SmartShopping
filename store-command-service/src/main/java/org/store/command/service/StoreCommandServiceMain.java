package org.store.command.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.store.command.service.StoreCommandServiceMain.MyConfiguration;
import org.store.command.service.aggregate.PriceTagAggregate;
import org.store.command.service.command.PriceTagCommand;
import org.store.command.service.service.CommandService;
import org.store.command.service.service.QueryService;
import org.store.domain.service.repository.StoreRepository;
import org.store.domain.service.repository.PriceTagRepository;
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
public class StoreCommandServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(StoreCommandServiceMain.class, args);
    }

    @Configuration
    @ComponentScan(basePackages = {"org.store.command.service", "org.store.domain.service"})
    @EntityScan(basePackages = {"org.store.command.service", "org.store.domain.service"})
    @EnableJpaRepositories(basePackages = {"org.store.domain.service.repository"})
    @EnableEventHandlers
    class MyConfiguration extends WebMvcConfigurerAdapter {

        @Bean
        public AggregateRepository<PriceTagAggregate, PriceTagCommand> aggregateRepository(
                EventuateAggregateStore eventuateAggregateStore) {
            return new AggregateRepository<>(PriceTagAggregate.class, eventuateAggregateStore);
        }

        @Bean
        public CommandService commandService(AggregateRepository<PriceTagAggregate, PriceTagCommand> aggregateRepository,
                                             QueryService queryService) {
            return new CommandService(aggregateRepository, queryService);
        }

        @Bean
        public QueryService queryService(StoreRepository storeRepository, PriceTagRepository priceTagRepository) {
            return new QueryService(storeRepository, priceTagRepository);
        }
    }
}
