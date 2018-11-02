package org.user.command.service;

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
import org.user.command.service.CartCommandServiceMain.MyConfiguration;
import org.user.command.service.aggregate.UserAggregate;
import org.user.command.service.command.UserCommand;
import org.user.command.service.controller.Controller;
import org.user.command.service.service.CommandService;
import org.user.domain.service.repository.UserRepository;

@Configuration
@Import({MyConfiguration.class, EventuateDriverConfiguration.class})
@EnableAutoConfiguration
public class CartCommandServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(CartCommandServiceMain.class, args);
    }

    @Configuration
    @ComponentScan(basePackages = {"org.user.command.service", "org.user.domain.service"})
    @EntityScan(basePackages = {"org.user.command.service", "org.user.domain.service"})
    @EnableJpaRepositories(basePackages = {"org.user.domain.service.repository"})
    @EnableEventHandlers
    class MyConfiguration extends WebMvcConfigurerAdapter {

        @Bean
        public AggregateRepository<UserAggregate, UserCommand> aggregateRepository(
                EventuateAggregateStore eventuateAggregateStore) {
            return new AggregateRepository<>(UserAggregate.class, eventuateAggregateStore);
        }

        @Bean
        public CommandService commandService(AggregateRepository<UserAggregate, UserCommand> aggregateRepository) {
            return new CommandService(aggregateRepository);
        }

        @Bean
        public Controller controller(CommandService commandService, UserRepository userRepository) {
            return new Controller(commandService, userRepository);
        }
    }
}
