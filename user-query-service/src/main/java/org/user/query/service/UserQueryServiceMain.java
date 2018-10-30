package org.user.query.service;

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
import org.user.domain.service.repository.UserRepository;
import org.user.query.service.UserQueryServiceMain.MyConfiguration;
import org.user.query.service.service.QueryService;
import org.user.query.service.subscriber.QueryEventSubscriber;

@Configuration
@Import({MyConfiguration.class, EventuateDriverConfiguration.class})
@EnableAutoConfiguration
public class UserQueryServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(UserQueryServiceMain.class, args);
    }

    @Configuration
    @ComponentScan(basePackages = {"org.user.query.service", "org.user.domain.service"})
    @EntityScan(basePackages = {"org.user.query.service", "org.user.domain.service"})
    @EnableJpaRepositories(basePackages = {"org.user.domain.service.repository"})
    @EnableEventHandlers
    class MyConfiguration extends WebMvcConfigurerAdapter {

        @Bean
        public QueryEventSubscriber queryEventSubscriber(QueryService service) {
            return new QueryEventSubscriber(service);
        }

        @Bean
        public QueryService queryService(UserRepository repository) {
            return new QueryService(repository);
        }
    }
}
