package org.user.command.service;

import org.user.command.service.UserCommandServiceMain.MyConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.user.command.service.aggregate.UserAggregate;
import org.user.command.service.aggregate.UserBulkDeleteAggregate;
import org.user.command.service.command.UserCommand;
import org.user.command.service.service.UserCommandService;

import org.user.command.service.subscriber.UserCommandEventSubscriber;
import org.user.domain.service.repository.UserRepository;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;

@Configuration
@Import({MyConfiguration.class, EventuateDriverConfiguration.class})
@EnableAutoConfiguration
public class UserCommandServiceMain {

	public static void main(String[] args) {
		SpringApplication.run(UserCommandServiceMain.class, args);
	}
	@Configuration
    @ComponentScan(basePackages = {"org.user.command.service", "org.user.domain.service"})
	@EntityScan(basePackages = {"org.user.command.service","org.user.domain.service"})
	@EnableJpaRepositories(basePackages = {"org.user.domain.service.repository"})
	@EnableEventHandlers
    class MyConfiguration extends WebMvcConfigurerAdapter {
		@Bean
        public AggregateRepository<UserAggregate,UserCommand>
		userAggregateRepository(EventuateAggregateStore eventuateAggregateStore){
			return new AggregateRepository<>(UserAggregate.class,eventuateAggregateStore);
		}
		
		@Bean
        public AggregateRepository<UserBulkDeleteAggregate,UserCommand>
		UserBulkDeleteAggregateRepository(EventuateAggregateStore eventuateAggregateStore){
			return new AggregateRepository<>(UserBulkDeleteAggregate.class,eventuateAggregateStore);
		}
		
		@Bean
        public UserCommandService userCommandService(AggregateRepository<UserAggregate,UserCommand> aggregateRepository,
        		AggregateRepository<UserBulkDeleteAggregate, UserCommand> bulkDeleteAggregateRepository	) {
			return new UserCommandService(aggregateRepository, bulkDeleteAggregateRepository);
			
		}
		
		@Bean
		public UserCommandEventSubscriber userCommandEventSubscriber() {
			return new UserCommandEventSubscriber();
		}
		
	}
}
