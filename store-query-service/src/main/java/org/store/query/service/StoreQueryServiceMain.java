package org.store.query.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.store.domain.repository.PriceTagRepository;
import org.store.domain.repository.StoreRepository;
import org.store.query.service.StoreQueryServiceMain.MyConfiguration;
import org.store.query.service.subscriber.QueryEventSubscriber;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;

@Configuration
@Import({MyConfiguration.class, EventuateDriverConfiguration.class})
@EnableAutoConfiguration
public class StoreQueryServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(StoreQueryServiceMain.class, args);
    }

    @Configuration
    @ComponentScan(basePackages = {"org.store.query.service", "org.store.domain", "org.utils"})
    @EntityScan(basePackages = {"org.store.query.service", "org.store.domain", "org.utils"})
    @EnableJpaRepositories(basePackages = {"org.store.domain.repository"})
    @EnableEventHandlers
    class MyConfiguration extends WebMvcConfigurerAdapter {

        @Bean
        public QueryEventSubscriber queryEventSubscriber(StoreRepository storeRepository,
                                                         PriceTagRepository priceTagRepository) {
            return new QueryEventSubscriber(storeRepository, priceTagRepository);
        }
    }
}
