package org.gateway.service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.gateway.service.aggregate.GatewayAggregate;
import org.gateway.service.command.GatewayCommand;
import org.gateway.service.controller.Controller;
import org.gateway.service.service.GatewayService;
import org.gateway.service.GatewayServiceMain.MyConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.gateway.service.subscriber.GatewayEventSubscriber;

@Configuration
@Import({MyConfiguration.class, EventuateDriverConfiguration.class})
@EnableAutoConfiguration
public class GatewayServiceMain {

  public static void main(String[] args) {

    SpringApplication.run(GatewayServiceMain.class, args);
  }
  
  
  @Configuration
  @ComponentScan(basePackages = {"org.gateway.service"})
  @EntityScan(basePackages = {"org.gateway.service"})
  //@EnableJpaRepositories(basePackages = {"org.cart.domain.repository"})
  @EnableEventHandlers
  class MyConfiguration extends WebMvcConfigurerAdapter {
	  /*
      @Bean
      public AggregateRepository<GatewayAggregate, GatewayCommand> aggregateRepository(
              EventuateAggregateStore eventuateAggregateStore) {
          return new AggregateRepository<>(GatewayAggregate.class, eventuateAggregateStore);
      }
      
      
      @Bean
      public CommandService commandService(AggregateRepository<GatewayAggregate, GatewayCommand> aggregateRepository,
                                           ProductRepository productRepository, CartRepository cartRepository,
                                           CommandEventSubscriber commandEventSubscriber) {
          return new CommandService(aggregateRepository, productRepository, cartRepository, commandEventSubscriber);
      }
	*/
      
      @Bean
      public Controller controller(GatewayService gatewayService) {
          return new Controller(gatewayService);
      }

      @Bean
      public GatewayEventSubscriber GatewayEventSubscriber() {
          return new GatewayEventSubscriber();
      }
  }
  
  
  
}
