package org.store.query.service.controller;

import org.springframework.web.bind.annotation.*;
import org.store.domain.dao.StoreDao;
import org.store.query.service.service.QueryService;

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
    public CompletableFuture<StoreDao> findAll() throws Exception {
        if (!this.queryService.isIdentified()) {
            throw new Exception("The store isn't identified !");
        }
        return CompletableFuture
                .supplyAsync(() -> this.queryService.findSingleton());
    }
}
