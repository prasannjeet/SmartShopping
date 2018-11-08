package org.gateway.command.service.controller;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.user.domain.model.User;

@RestController
@RequestMapping(value = "/")
@ResponseBody
public class GatewayController {
	
	String serviceIp = "192.168.99.100";
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
    
    @GetMapping(value = "/users")
    @ResponseBody
    public ResponseEntity users() throws URISyntaxException
    {        
    	int port = 7082;
        URI uri = new URI("http", null, serviceIp, port, "/", null, null);
        //URI uri = new URI("http://www.example.com");
        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity responseEntity =
            restTemplate.exchange(uri, HttpMethod.GET, null, String.class);

        return responseEntity;
    }
    
    @PostMapping(value = "/users", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity createUser(@RequestBody @Valid User user) throws URISyntaxException
    {        
    	int port = 7081;
        URI uri = new URI("http", null, serviceIp, port, "/", null, null);
        //URI uri = new URI("http://www.example.com");
        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity responseEntity =
            restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<User>(user), String.class);

        return responseEntity;
    }
}
