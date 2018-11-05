package org.gateway.service;

import java.net.InetAddress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class GatewayServiceMain {

  public static void main(String[] args) {

    SpringApplication.run(GatewayServiceMain.class, args);
  }
  
  
  @RequestMapping(value = "/ip")
  public String getIp() throws Exception {
	  InetAddress ip;
	  ip = InetAddress.getLocalHost();
      return ip.getHostAddress();
  }
  
}
