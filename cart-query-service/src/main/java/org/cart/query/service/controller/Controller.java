package org.cart.query.service.controller;

import org.cart.domain.dao.CartResponseDao;
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
    public CompletableFuture<List<CartResponseDao>> findAll() {
        return CompletableFuture
                .supplyAsync(() -> this.queryService.findAll());
    }

    @GetMapping("/{userId}")
    public CompletableFuture<CartResponseDao> findByUserId(@PathVariable @NotBlank String userId) {
        return CompletableFuture
                .supplyAsync(() -> this.queryService.findByUserId(userId));
    }
}
