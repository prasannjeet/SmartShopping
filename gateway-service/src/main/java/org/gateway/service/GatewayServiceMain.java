package org.gateway.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayServiceMain {

  public static void main(String[] args) {
	System.getProperties().put( "server.port", 8888);  //8888 port is set here
    SpringApplication.run(GatewayServiceMain.class, args);
  }
 
}
