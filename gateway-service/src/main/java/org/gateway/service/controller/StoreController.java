package org.gateway.service.controller;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StoreController {
	 
	  @Bean
	  public RouteLocator StoreRoutes(RouteLocatorBuilder builder) {
	  return builder.routes()
	       .route(p -> p
	           .path("/store")
	           .uri("http://192.168.0.144:8082"))
	       .route(p -> p
		           .path("/store/suggestion")
		           .uri("http://192.168.0.144:8082"))
	       .route(p -> p
		           .path("/store/browse")
		           .uri("http://192.168.0.144:8082"))
	       .build();
	   }
}

