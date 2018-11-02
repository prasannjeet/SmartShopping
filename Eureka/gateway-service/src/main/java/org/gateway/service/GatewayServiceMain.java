package org.gateway.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  
  @RestController
  class ServiceInstanceRestController {

      @Autowired
      private DiscoveryClient discoveryClient;

      @RequestMapping("/service-instances/")
      public List<String> serviceInstancesByApplicationName() {
          return this.discoveryClient.getServices();
      }
  }
}
