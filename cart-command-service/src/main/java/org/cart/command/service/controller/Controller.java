package org.cart.command.service.controller;

import org.cart.command.service.service.CartCommandService;
import org.cart.command.service.service.CartQueryService;
import org.cart.domain.service.model.Cart;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/carts", produces = "application/json")
public class Controller {

    private CartCommandService cartCommandService;
    private CartQueryService cartQueryService;

    public Controller(CartCommandService cartCommandService, CartQueryService cartQueryService) {
        this.cartCommandService = cartCommandService;
        this.cartQueryService = cartQueryService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseBody
    public CompletableFuture<ResponseEntity<?>> create(@RequestBody @Valid Cart cart) {
        return this.cartCommandService.create(cart)
                .handle((cartAggregate, error) ->
                        ResponseEntity.status(error == null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST)
                                .body(error == null ? this.cartQueryService.findByUserId(cartAggregate.getAggregate().getCart().getUserId()) : error));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public CompletableFuture<ResponseEntity<?>> deleteById(@PathVariable String id) {
        return this.cartCommandService.delete(id)
                .handle((cartAggregate, error) ->
                        ResponseEntity.status(error == null ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                                .body(error == null ? this.cartQueryService.findByUserId(cartAggregate.getAggregate().getCart().getUserId()) : error));
    }

    @DeleteMapping("/users/{userId}")
    @ResponseBody
    public CompletableFuture<ResponseEntity<?>> deleteByUserId(@PathVariable String userId) {
        return this.cartCommandService.delete(this.cartQueryService.findByUserId(userId).getId())
                .handle((cartAggregate, error) ->
                        ResponseEntity.status(error == null ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                                .body(error == null ? this.cartQueryService.findByUserId(cartAggregate.getAggregate().getCart().getUserId()) : error));
    }
}
