package org.store.query.service.controller;

import org.store.domain.service.dao.StoreDao;
import org.store.query.service.service.StoreQueryService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/stores", produces = "application/json")
@ResponseBody
public class Controller {

    private StoreQueryService storeQueryService;

    public Controller(StoreQueryService storeQueryService) {
        this.storeQueryService = storeQueryService;
    }

    @GetMapping
    public CompletableFuture<List<StoreDao>> findAll() {
        return CompletableFuture
                .supplyAsync(() -> this.storeQueryService.findAll());
    }
    
    @GetMapping("/{storeId}")
    public CompletableFuture<StoreDao> findByUserId(@PathVariable @NotBlank String storeId) {
        return CompletableFuture
                .supplyAsync(() -> this.storeQueryService.findByStoreId(storeId));
    }
    
    @GetMapping("/nearby")
    public CompletableFuture<List<StoreDao>> findNearby() {
        return CompletableFuture
                .supplyAsync(() -> this.storeQueryService.findNearby());
    }
}