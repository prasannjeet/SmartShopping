package org.gateway.service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayServiceMain {

  public static void main(String[] args) {

	System.getProperties().put( "server.port", 8888);  //8888 port is set here
    SpringApplication.run(GatewayServiceMain.class, args);
  }
  
  @Bean
  public RouteDefinitionLocator discoveryClientRouteDefinitionLocator(DiscoveryClient discoveryClient) {
      return new DiscoveryClientRouteDefinitionLocator(discoveryClient, null);
  }
  
}
