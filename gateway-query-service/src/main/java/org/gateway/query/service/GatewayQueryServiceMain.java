package org.gateway.query.service;

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
import org.user.domain.repository.UserRepository;
import org.gateway.query.service.GatewayQueryServiceMain.MyConfiguration;
import org.gateway.query.service.controller.Controller;
import org.gateway.query.service.service.QueryService;
import org.gateway.query.service.subscriber.QueryEventSubscriber;

@Configuration
@Import({MyConfiguration.class, EventuateDriverConfiguration.class})
@EnableAutoConfiguration
public class GatewayQueryServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(GatewayQueryServiceMain.class, args);
    }

    @Configuration
    @ComponentScan(basePackages = {"org.user.query.service", "org.user.domain", "org.utils"})
    @EntityScan(basePackages = {"org.user.query.service", "org.user.domain", "org.utils"})
    @EnableJpaRepositories(basePackages = {"org.user.domain.repository"})
    @EnableEventHandlers
    class MyConfiguration extends WebMvcConfigurerAdapter {

        @Bean
        public QueryEventSubscriber queryEventSubscriber(UserRepository userRepository) {
            return new QueryEventSubscriber(userRepository);
        }

        @Bean
        public QueryService queryService(UserRepository userRepository) {
            return new QueryService(userRepository);
        }

        @Bean
        public Controller controller(QueryService queryService) {
            return new Controller(queryService);
        }
    }
}
