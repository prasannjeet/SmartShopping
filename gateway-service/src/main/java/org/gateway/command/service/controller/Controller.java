package org.gateway.command.service.controller;

import org.gateway.command.service.service.CommandService;
import org.gateway.domain.model.Product;
import org.gateway.domain.model.StoreInfos;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.store.domain.model.PriceTag;
import org.store.domain.model.Store;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.core.UriBuilder;

@RestController
@RequestMapping(value = "/")
@ResponseBody
public class Controller {

    private CommandService commandService;

    public Controller(CommandService commandService) {
        this.commandService = commandService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping(value = "/ip")
    public String getIp(HttpServletRequest request) throws Exception {
    	String uri1 = request.getRequestURL().toString();
    	String uri2 = UriBuilder.fromUri(request.getRequestURL().toString()).port(7082).build().toString();
    	String uri3 = UriBuilder.fromUri(new URI(request.getRequestURL().toString())).port(7082).build().toString();
    	return uri1 + " ; " + uri2 + ";" + uri3;
    }

    @GetMapping(value = "/example")
    @ResponseBody
    public String mirrorRest(HttpServletRequest request) throws URISyntaxException {
        //URI uri = new URI("http", null, server, port, request.getRequestURL().toString(), request.getQueryString(), null);
        URI uri = new URI("http://www.example.com");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(uri, HttpMethod.valueOf(request.getMethod()), null, String.class);

        return responseEntity.getBody();
    }

    //********USERS*******************

    @GetMapping(value = "/users")
    @ResponseBody
    public ResponseEntity users(HttpServletRequest request) throws URISyntaxException {
    	URI uri = UriBuilder.fromUri(request.getRequestURL().toString()).port(7082).build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity =
                restTemplate.exchange(uri, HttpMethod.valueOf(request.getMethod()), null, String.class);

        return responseEntity;
    }

    @PostMapping(value = "/users", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity createUser(HttpServletRequest request, @RequestBody Object body) throws URISyntaxException {
    	URI uri = UriBuilder.fromUri(request.getRequestURL().toString()).port(7081).build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity =
                restTemplate.exchange(uri, HttpMethod.valueOf(request.getMethod()), new HttpEntity(body), String.class);

        return responseEntity;
    }

    @GetMapping(value = "/users/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity getUser(HttpServletRequest request, @NotBlank @PathVariable String id) throws URISyntaxException {
    	URI uri = UriBuilder.fromUri(request.getRequestURL().toString()).port(7082).build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity =
                restTemplate.exchange(uri, HttpMethod.valueOf(request.getMethod()), null, String.class);

        return responseEntity;
    }

    @DeleteMapping(value = "/users/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity deleteUser(HttpServletRequest request, @NotBlank @PathVariable String id) throws URISyntaxException {
    	URI uri = UriBuilder.fromUri(request.getRequestURL().toString()).port(7081).build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity =
                restTemplate.exchange(uri, HttpMethod.valueOf(request.getMethod()), null, String.class);

        return responseEntity;
    }

    //********CARTS****************

    @GetMapping(value = "/carts")
    public ResponseEntity getCarts(HttpServletRequest request) throws URISyntaxException {
    	URI uri = UriBuilder.fromUri(request.getRequestURL().toString()).port(7084).build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity =
                restTemplate.exchange(uri, HttpMethod.valueOf(request.getMethod()), null, String.class);

        return responseEntity;
    }

    @GetMapping(value = "/carts/{userId}")
    @ResponseBody
    public ResponseEntity getCart(HttpServletRequest request, @NotBlank @PathVariable String userId) throws URISyntaxException {
    	URI uri = UriBuilder.fromUri(request.getRequestURL().toString()).port(7084).build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity =
                restTemplate.exchange(uri, HttpMethod.valueOf(request.getMethod()), null, String.class);

        return responseEntity;
    }

    @PostMapping(value = "/carts/products", consumes = "application/json")
    @ResponseBody
    public ResponseEntity addItemToCart(HttpServletRequest request, @RequestBody Object object) throws URISyntaxException {
    	URI uri = UriBuilder.fromUri(request.getRequestURL().toString()).port(7083).build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity =
                restTemplate.exchange(uri, HttpMethod.valueOf(request.getMethod()), new HttpEntity(object), String.class);

        return responseEntity;
    }

    @PutMapping(value = "/carts/products", consumes = "application/json")
    @ResponseBody
    public ResponseEntity updateItemInCart(HttpServletRequest request, @RequestBody Object body) throws URISyntaxException {
    	URI uri = UriBuilder.fromUri(request.getRequestURL().toString()).port(7083).build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity =
                restTemplate.exchange(uri, HttpMethod.valueOf(request.getMethod()), new HttpEntity(body), String.class);

        return responseEntity;
    }

    @DeleteMapping(value = "/carts/{userId}/products/{barcode}")
    @ResponseBody
    public ResponseEntity updateItemInCart(HttpServletRequest request, @NotBlank @PathVariable String userId, @NotBlank @PathVariable String barcode) throws URISyntaxException {
    	URI uri = UriBuilder.fromUri(request.getRequestURL().toString()).port(7083).build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity =
                restTemplate.exchange(uri, HttpMethod.valueOf(request.getMethod()), null, String.class);

        return responseEntity;
    }

    @PutMapping(value = "/carts/sort/stores-distance")
    @ResponseBody
    public ResponseEntity sortCartByDistance(HttpServletRequest request, @RequestBody Object body) throws URISyntaxException {
    	URI uri = UriBuilder.fromUri(request.getRequestURL().toString()).port(7083).build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity =
                restTemplate.exchange(uri, HttpMethod.valueOf(request.getMethod()), new HttpEntity(body), String.class);

        return responseEntity;
    }

    @PutMapping(value = "/carts/sort/products-price")
    @ResponseBody
    public ResponseEntity sortCartByPrice(HttpServletRequest request, @RequestBody Object body) throws URISyntaxException {
    	URI uri = UriBuilder.fromUri(request.getRequestURL().toString()).port(7083).build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity =
                restTemplate.exchange(uri, HttpMethod.valueOf(request.getMethod()), new HttpEntity(body), String.class);
        
        return responseEntity;
    }


    //********PRODUCT************
    @GetMapping(value = "/products")
    @ResponseBody
    public ResponseEntity getProducts(HttpServletRequest request) throws URISyntaxException {
    	URI uri = UriBuilder.fromUri(request.getRequestURL().toString()).port(7085).build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity =
                restTemplate.exchange(uri, HttpMethod.valueOf(request.getMethod()), null, String.class);

        return responseEntity;
    }

    @GetMapping(value = "/products/{barcode}")
    @ResponseBody
    public ResponseEntity getProductByBarcode(HttpServletRequest request, @NotBlank @PathVariable String barcode) throws URISyntaxException {
    	URI uri = UriBuilder.fromUri(request.getRequestURL().toString()).port(7085).build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity =
                restTemplate.exchange(uri, HttpMethod.valueOf(request.getMethod()), null, String.class);

        return responseEntity;
    }

    //********STORES*******************

    @PostMapping(value = "/stores/init")
    @ResponseBody
    public ResponseEntity sortCartByPrice(@RequestBody @Valid StoreInfos storeInfos) throws URISyntaxException {
        Store response;

        try {
            response = commandService.initStore(storeInfos);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping(value = "/stores/{storeId}/product")
    @ResponseBody
    public ResponseEntity addProductToStore(@NotBlank @PathVariable String storeId, @RequestBody @Valid Product product) throws URISyntaxException {
        org.store.domain.model.Product response;
        try {
            response = commandService.addProductToStore(storeId, product);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping(value = "/stores/{storeId}/product")
    @ResponseBody
    public ResponseEntity updateProductInStore(@NotBlank @PathVariable String storeId, @RequestBody Product product) throws URISyntaxException {
        PriceTag response;
        try {
            response = commandService.updateProductInStore(storeId, product);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(value = "/stores/{storeId}/scrap")
    @ResponseBody
    public ResponseEntity initScrapStore(@NotBlank @PathVariable String storeId) throws URISyntaxException {
        Store response;
        try {
            response = commandService.scrapProduct(storeId);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
