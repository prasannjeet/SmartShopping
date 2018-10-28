package org.gateway.service.controller;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductController {
	 
	  @Bean
	  public RouteLocator ProductRoutes(RouteLocatorBuilder builder) {
	  return builder.routes()
	       .route(p -> p
	           .path("/product")
	           .uri("http://192.168.0.144:"))
	       .build();
	   }
	  
	  @Bean
	   public RouteLocator myRoutes(RouteLocatorBuilder builder) {
	   return builder.routes()
	        .route(p -> p
	            .path("/testing")
	            .filters(f -> f.rewritePath("/testing", "/get"))
	            .uri("http://httpbin.org:80"))
	        .route(p -> p
	                .path("/delete")
	                .uri("http://httpbin.org:80"))
	        .build();
	    }
	  
}

