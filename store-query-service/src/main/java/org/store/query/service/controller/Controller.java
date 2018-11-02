package org.store.query.service.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;
import org.store.domain.dao.StoreDao;
import org.store.domain.model.Store;
import org.store.query.service.service.QueryService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/stores", produces = "application/json")
@ResponseBody
public class Controller {

    private QueryService queryService;

    public Controller(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public CompletableFuture<List<Store>> findAll() {
        return CompletableFuture
                .supplyAsync(() -> this.queryService.findAll());
    }

    @GetMapping("/{id}")
    public CompletableFuture<StoreDao> findById(@NotBlank @PathVariable String id) {
        return CompletableFuture
                .supplyAsync(() -> this.queryService.findById(id));
    }
}
