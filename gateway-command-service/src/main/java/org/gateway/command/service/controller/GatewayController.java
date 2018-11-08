package org.gateway.command.service.controller;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
    //********USERS*******************
    
    @GetMapping(value = "/users")
    @ResponseBody
    public ResponseEntity users() throws URISyntaxException
    {        
    	int port = 7082;
        URI uri = new URI("http", null, serviceIp, port, "/", null, null);
        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity responseEntity =
            restTemplate.exchange(uri, HttpMethod.GET, null, String.class);

        return responseEntity;
    }
    
    @PostMapping(value = "/users", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity createUser(@RequestBody @Valid Object body) throws URISyntaxException
    {        
    	int port = 7081;
        URI uri = new URI("http", null, serviceIp, port, "/", null, null);
        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity responseEntity =
            restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity(body), String.class);

        return responseEntity;
    }
    
    
    @DeleteMapping(value = "/users/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity deleteUser(@NotBlank @PathVariable String id) throws URISyntaxException
    {        
    	int port = 7081;
        URI uri = new URI("http", null, serviceIp, port, "/"+id, null, null);
        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity responseEntity =
            restTemplate.exchange(uri, HttpMethod.DELETE, null, String.class);

        return responseEntity;
    }
    
    //********CARTS****************
    
    @GetMapping(value = "/carts/{userId}")
    @ResponseBody
    public ResponseEntity getCart(@NotBlank @PathVariable String userId) throws URISyntaxException
    {        
    	int port = 7084;
        URI uri = new URI("http", null, serviceIp, port, "/carts/"+userId, null, null);
        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity responseEntity =
            restTemplate.exchange(uri, HttpMethod.GET, null, String.class);

        return responseEntity;
    }
    
    @PostMapping(value = "/carts/products", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addItemToCart(@RequestBody Object object) throws URISyntaxException
    {        
    	int port = 7083;
        URI uri = new URI("http", null, serviceIp, port, "/carts/products", null, null);
        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity responseEntity =
            restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity(object), String.class);

        return responseEntity;
    }
    
    @PutMapping(value = "/carts/products", consumes = "application/json")
    @ResponseBody
    public ResponseEntity updateItemInCart(@RequestBody Object body) throws URISyntaxException
    {        
    	int port = 7083;
        URI uri = new URI("http", null, serviceIp, port, "/carts/products", null, null);
        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity responseEntity =
            restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity(body), String.class);

        return responseEntity;
    }
    
    @DeleteMapping(value = "/carts/{userId}/products/{barcode}")
    @ResponseBody
    public ResponseEntity updateItemInCart(@NotBlank @PathVariable String userId, @NotBlank @PathVariable String barcode) throws URISyntaxException
    {        
    	int port = 7083;
        URI uri = new URI("http", null, serviceIp, port, "/carts/"+userId+"/products/"+barcode, null, null);
        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity responseEntity =
            restTemplate.exchange(uri, HttpMethod.DELETE, null, String.class);

        return responseEntity;
    }
    
    @PutMapping(value = "/carts/sort/distance")
    @ResponseBody
    public ResponseEntity sortCartByDistance(@RequestBody Object body) throws URISyntaxException
    {        
    	int port = 7083;
        URI uri = new URI("http", null, serviceIp, port, "/carts/sort/stores-distance", null, null);
        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity responseEntity =
            restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity(body), String.class);

        return responseEntity;
    }
    
    @PutMapping(value = "/carts/sort/price")
    @ResponseBody
    public ResponseEntity sortCartByPrice(@RequestBody Object body) throws URISyntaxException
    {        
    	int port = 7083;
        URI uri = new URI("http", null, serviceIp, port, "/carts/sort/products-price", null, null);
        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity responseEntity =
            restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity(body), String.class);

        return responseEntity;
    }
    
    
    //********PRODUCT************
    @GetMapping(value = "/products")
    @ResponseBody
    public ResponseEntity getProducts() throws URISyntaxException
    {        
    	int port = 7085;
        URI uri = new URI("http", null, serviceIp, port, "/products", null, null);
        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity responseEntity =
            restTemplate.exchange(uri, HttpMethod.GET, null, String.class);

        return responseEntity;
    }
    
    @GetMapping(value = "/products/{barcode}")
    @ResponseBody
    public ResponseEntity getProductByBarcode(@NotBlank @PathVariable String barcode) throws URISyntaxException
    {        
    	int port = 7085;
        URI uri = new URI("http", null, serviceIp, port, "/products/"+barcode, null, null);
        RestTemplate restTemplate= new RestTemplate();
        ResponseEntity responseEntity =
            restTemplate.exchange(uri, HttpMethod.GET, null, String.class);

        return responseEntity;
    }
}