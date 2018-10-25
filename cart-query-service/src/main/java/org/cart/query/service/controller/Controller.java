package org.cart.query.service.controller;

import org.cart.query.service.service.CartQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/carts", produces = "application/json")
public class Controller {

    private CartQueryService cartQueryService;

    public Controller(CartQueryService cartQueryService) {
        this.cartQueryService = cartQueryService;
    }
    
    @GetMapping
    public CompletableFuture<ResponseEntity<?>> findAll() {
        return CompletableFuture
                .supplyAsync(() -> this.cartQueryService.findAll())
                .handle((carts, error) ->
                        ResponseEntity.status(error == null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(error == null ? carts : error));
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<?>> findById(@PathVariable String id) {
        return CompletableFuture
                .supplyAsync(() -> this.cartQueryService.findById(id))
                .handle((cart, error) ->
                        ResponseEntity.status(error == null ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                                .body(error == null ? cart : error));
    }

    @GetMapping("/users/{userId}")
    public CompletableFuture<ResponseEntity<?>> findByUserId(@PathVariable String userId) {
        return CompletableFuture
                .supplyAsync(() -> this.cartQueryService.findByUserId(userId))
                .handle((cart, error) ->
                        ResponseEntity.status(error == null ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                                .body(error == null ? cart : error));
    }
}
