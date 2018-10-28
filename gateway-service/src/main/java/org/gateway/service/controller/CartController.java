package org.gateway.service.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartController {
	  @Autowired
      ApplicationArguments appArgs;
	  
	  
	  @Bean
	  public RouteLocator cartRoutes(RouteLocatorBuilder builder) {
		  List<String> arg = appArgs.getOptionValues("ip");  
		  
		  return builder.routes()
			   .route(p -> p
			           	.path("/carts")
			           	.uri("http://"+arg.get(0)+":8081"))
			   .route(p -> p
			           	.path("/carts/products")
			           	.uri("http://"+arg.get(0)+":8081"))
			   .route(p -> p
			           	.path("/carts/**/products/**")
			           	.uri("http://"+arg.get(0)+":8081"))
		       .route(p -> p
		    		   .method("GET").and()
		               .path("/carts/**")
		               .uri("http://"+arg.get(0)+":8082"))
		       .build();
	   }
}
