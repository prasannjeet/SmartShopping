package org.gateway.service.controller;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserController {
	 
	  @Bean
	  public RouteLocator UserRoutes(RouteLocatorBuilder builder) {
	  return builder.routes()
	       .route(p -> p
	           .path("/user")
	           .uri("http://192.168.0.144:"))
	       .build();
	   }
}

