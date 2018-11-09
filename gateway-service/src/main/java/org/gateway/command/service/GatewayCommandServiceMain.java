package org.gateway.command.service;

import java.net.URI;
import java.net.URISyntaxException;

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
import org.gateway.command.service.GatewayCommandServiceMain.MyConfiguration;
import org.gateway.command.service.aggregate.GatewayAggregate;
import org.gateway.command.service.command.GatewayCommand;
import org.gateway.command.service.controller.Controller;
import org.gateway.command.service.service.CommandService;

@Configuration
@Import({MyConfiguration.class, EventuateDriverConfiguration.class})
@EnableAutoConfiguration
public class GatewayCommandServiceMain {

    public static void main(String[] args) throws URISyntaxException {
    	URI uri = new URI("http", null, "192.168.99.100", 7082, "/", null, null);
    	System.out.println(uri.toString());
        SpringApplication.run(GatewayCommandServiceMain.class, args);
    }

    @Configuration
    @ComponentScan(basePackages = {"org.gateway.command.service", "org.gateway.domain", "org.utils"})
    @EntityScan(basePackages = {"org.gateway.command.service", "org.gateway.domain", "org.utils"})
    @EnableEventHandlers
    class MyConfiguration extends WebMvcConfigurerAdapter {

        @Bean
        public AggregateRepository<GatewayAggregate, GatewayCommand> aggregateRepository(
                EventuateAggregateStore eventuateAggregateStore) {
            return new AggregateRepository<>(GatewayAggregate.class, eventuateAggregateStore);
        }

        @Bean
        public CommandService commandService(AggregateRepository<GatewayAggregate, GatewayCommand> aggregateRepository) {
            return new CommandService(aggregateRepository);
        }

        @Bean
        public Controller controller(CommandService commandService) {
            return new Controller(commandService);
        }
    }
}
