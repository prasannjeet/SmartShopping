package org.store.query.service;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;

import org.store.domain.service.repository.StoreRepository;
import org.store.domain.service.repository.PriceTagRepository;
import org.store.query.service.StoreQueryServiceMain.MyConfiguration;
import org.store.query.service.service.StoreQueryService;
import org.store.query.service.subscriber.QueryEventSubscriber;
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
public class StoreQueryServiceMain {

	public static void main(String[] args) {
        SpringApplication.run(StoreQueryServiceMain.class, args);
    }

    @Configuration
    @ComponentScan(basePackages = {"org.store.query.service", "org.store.domain.service"})
    @EntityScan(basePackages = {"org.store.query.service", "org.store.domain.service"})
    @EnableJpaRepositories(basePackages = {"org.store.domain.service.repository"})
    @EnableEventHandlers
    class MyConfiguration extends WebMvcConfigurerAdapter {

        @Bean
        public QueryEventSubscriber queryEventSubscriber(StoreQueryService queryService) {
            return new QueryEventSubscriber(queryService);
        }

        @Bean
        public StoreQueryService queryService(StoreRepository storeRepository, PriceTagRepository priceTagRepository) {
            return new StoreQueryService(storeRepository, priceTagRepository);
        }
    }
}
