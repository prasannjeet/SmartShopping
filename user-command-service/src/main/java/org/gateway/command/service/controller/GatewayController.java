package org.gateway.command.service.controller;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/")
@ResponseBody
public class GatewayController {
    @GetMapping("/hello")
    public String hello(){
    	return "hello world";
    }
    
    @GetMapping(value = "/ip")
    public String getIp() throws Exception {
  	  InetAddress ip;
  	  ip = InetAddress.getLocalHost();
        return ip.getHostAddress();
    }

    @GetMapping(value = "/example")
    @ResponseBody
    public String mirrorRest(HttpServletRequest request) throws URISyntaxException
    {
        String server = "example.com";
        int port = 80;
        
        //URI uri = new URI("http", null, server, port, request.getRequestURI(), request.getQueryString(), null);
        URI uri = new URI("http://www.example.com");
        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity<String> responseEntity =
            restTemplate.exchange(uri, HttpMethod.GET, null, String.class);

        return responseEntity.getBody();
    }
}
