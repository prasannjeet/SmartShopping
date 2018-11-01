package org.cart.query.service.controller;

import org.cart.domain.service.dao.CartDaoForEndUser;
import org.cart.query.service.service.QueryService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/carts", produces = "application/json")
@ResponseBody
public class Controller {

    private QueryService queryService;

    public Controller(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public CompletableFuture<List<CartDaoForEndUser>> findAllCarts() {
        return CompletableFuture
                .supplyAsync(() -> this.queryService.findAllCarts());
    }

    @GetMapping("/{userId}")
    public CompletableFuture<CartDaoForEndUser> findCartByUserId(@PathVariable @NotBlank String userId) {
        return CompletableFuture
                .supplyAsync(() -> this.queryService.findCartByUserId(userId));
    }
}
