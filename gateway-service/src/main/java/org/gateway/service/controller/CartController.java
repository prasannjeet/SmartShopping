package org.gateway.service.controller;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartController {
	 @Bean
	   public RouteLocator myRoutes(RouteLocatorBuilder builder) {
	   return builder.routes()
	        .route(p -> p
	            .path("/get")
	            .uri("http://httpbin.org:80"))
	        .route(p -> p
	                .path("/delete")
	                .uri("http://httpbin.org:80"))
	        .build();
	    }
	  
	  @Bean
	  public RouteLocator cartRoutes(RouteLocatorBuilder builder) {
	  return builder.routes()
	       .route(p -> p
	           .path("/carts")
	           .uri("http://192.168.0.144:8082"))
	       .route(p -> p
	               .path("/carts/**")
	               .filters(f -> f.rewritePath("/carts/(?<CID>.*)", "/${CID}"))
	               .uri("http://192.168.0.144:8082"))
	       .build();
	   }
}
