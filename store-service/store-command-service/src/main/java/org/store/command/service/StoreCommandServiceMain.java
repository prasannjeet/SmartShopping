package org.store.command.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.store.command.service.StoreCommandServiceMain.MyConfiguration;
import org.store.command.service.aggregate.StoreAggregate;
import org.store.command.service.aggregate.StoreBulkDeleteAggregate;
import org.store.command.service.command.StoreCommand;
import org.store.command.service.service.StoreCommandService;
import org.store.command.service.service.StoreQueryService;
import org.store.command.service.subscriber.StoreCommandEventSubscriber;
import org.store.domain.service.repository.StoreRepository;
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
        public AggregateRepository<StoreAggregate, StoreCommand>
        storeAggregateRepository(EventuateAggregateStore eventuateAggregateStore) {
            return new AggregateRepository<>(StoreAggregate.class, eventuateAggregateStore);
        }

        @Bean
        public AggregateRepository<StoreBulkDeleteAggregate, StoreCommand>
        storeBulkDeleteAggregateRepository(EventuateAggregateStore eventuateAggregateStore) {
            return new AggregateRepository<>(StoreBulkDeleteAggregate.class, eventuateAggregateStore);
        }

        @Bean
        public StoreCommandService storeCommandService(AggregateRepository<StoreAggregate, StoreCommand> aggregateRepository,
                                                     AggregateRepository<StoreBulkDeleteAggregate, StoreCommand> bulkDeleteAggregateRepository) {
            return new StoreCommandService(aggregateRepository, bulkDeleteAggregateRepository);
        }

        @Bean
        public StoreQueryService storeQueryService(StoreRepository storeRepository) {
            return new StoreQueryService(storeRepository);
        }

        @Bean
        public StoreCommandEventSubscriber storeCommandEventSubscriber() {
            return new StoreCommandEventSubscriber();
        }
    }
}
