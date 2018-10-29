package org.cart.query.service.controller;

import org.cart.domain.service.dao.CartDao;
import org.cart.query.service.service.CartQueryService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/carts", produces = "application/json")
@ResponseBody
public class Controller {

    private CartQueryService cartQueryService;

    public Controller(CartQueryService cartQueryService) {
        this.cartQueryService = cartQueryService;
    }

    @GetMapping
    public CompletableFuture<List<CartDao>> findAll() {
        return CompletableFuture
                .supplyAsync(() -> this.cartQueryService.findAll());
    }

    @GetMapping("/{userId}")
    public CompletableFuture<CartDao> findByUserId(@PathVariable @NotBlank String userId) {
        return CompletableFuture
                .supplyAsync(() -> this.cartQueryService.findByUserId(userId));
    }
}
