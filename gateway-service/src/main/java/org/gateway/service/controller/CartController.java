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
						.path("/get_cart_by_ID/**")
						.and().method("GET")
						.filters(rw -> rw.rewritePath("/get_cart_by_ID/(?<UID>.*)", "/carts/${UID}"))
						.uri("http://"+arg.get(0)+":8082"))
				.route(p -> p
						.path("/product_to_cart")
						.and().method("POST")
						.filters(rw -> rw.rewritePath("/product_to_cart", "/carts/products"))
						.uri("http://"+arg.get(0)+":8081"))
				.route(p -> p
						.path("/update_product_quantity/**/**/**")
						.and().method("PUT")
						.filters(rw -> rw.rewritePath("/update_product_quantity/(?<UID>.*)/(?<PID>.*)/(?<Q>.*)", "/carts/${UID}/products/${PID}/${Q}"))
						.uri("http://"+arg.get(0)+":8081"))
				.route(p -> p
						.path("/remove_cart_product/**/**")
						.and().method("DELETE")
						.filters(rw -> rw.rewritePath("/remove_cart_product/(?<UID>.*)/(?<PID>.*)", "/carts/${UID}/products/${PID}"))
						.uri("http://"+arg.get(0)+":8081"))
				.build();
	}

}
