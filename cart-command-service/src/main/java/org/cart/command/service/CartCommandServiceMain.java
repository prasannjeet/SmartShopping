package org.cart.command.service;

import org.cart.command.service.CartCommandServiceMain.MyConfiguration;
import org.cart.command.service.aggregate.CartAggregate;
import org.cart.command.service.aggregate.CartBulkDeleteAggregate;
import org.cart.command.service.command.CartCommand;
import org.cart.command.service.service.CartCommandService;
import org.cart.command.service.service.CartQueryService;
import org.cart.command.service.subscriber.CartCommandEventSubscriber;
import org.cart.domain.service.repository.CartRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;

@Configuration
@Import({ MyConfiguration.class, EventuateDriverConfiguration.class })
@EnableAutoConfiguration
public class CartCommandServiceMain {

	public static void main(String[] args) {
		SpringApplication.run(CartCommandServiceMain.class, args);
	}

	@Configuration
	@ComponentScan(basePackages = { "org.cart.command.service", "org.cart.domain.service" })
	@EntityScan(basePackages = { "org.cart.command.service", "org.cart.domain.service" })
	@EnableJpaRepositories(basePackages = { "org.cart.domain.service.repository" })
	@EnableEventHandlers
	class MyConfiguration extends WebMvcConfigurerAdapter {

		@Bean
		public AggregateRepository<CartAggregate, CartCommand> aggregateRepository(EventuateAggregateStore eventStore) {
			return new AggregateRepository<>(CartAggregate.class, eventStore);
		}

		@Bean
		public AggregateRepository<CartBulkDeleteAggregate, CartCommand> bulkDeleteAggregateRepository(
				EventuateAggregateStore eventStore) {
			return new AggregateRepository<>(CartBulkDeleteAggregate.class, eventStore);
		}

		@Bean
		public CartCommandService commandService(AggregateRepository<CartAggregate, CartCommand> aggregateRepository,
				AggregateRepository<CartBulkDeleteAggregate, CartCommand> bulkDeleteAggregateRepository) {
			return new CartCommandService(aggregateRepository, bulkDeleteAggregateRepository);
		}

		@Bean
		public CartQueryService queryService(CartRepository repository) {
			return new CartQueryService(repository);
		}
		
		@Bean
		public CartCommandEventSubscriber cartCommandEventSubscriber() {
			return new CartCommandEventSubscriber();
		}
	}
}
