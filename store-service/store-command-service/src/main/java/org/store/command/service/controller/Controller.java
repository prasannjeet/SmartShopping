package org.store.command.service.controller;

import org.store.command.service.service.StoreCommandService;
import org.store.command.service.service.StoreQueryService;
import org.store.domain.service.dao.StoreDao;
import org.store.domain.service.model.Store;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/stores", produces = "application/json")
@ResponseBody
public class Controller {

    private StoreCommandService storeCommandService;
    private StoreQueryService storeQueryService;

    public Controller(StoreCommandService storeCommandService, StoreQueryService storeQueryService) {
        this.storeCommandService = storeCommandService;
        this.storeQueryService = storeQueryService;
    }

    @PostMapping(consumes = "application/json")
    public CompletableFuture<StoreDao> createStore(@RequestBody @Valid Store store) throws Exception {
        if (this.storeQueryService.isDuplicate(store.getStoreName())) {
            throw new Exception("Duplicate storeName = " + store.getStoreName());
        }
        return this.storeCommandService
                .save(store)
                .thenApply(entity -> new StoreDao(entity.getEntityId(), entity.getAggregate().getStore()));
    }
    
    @PutMapping(value = "/{storeName}", consumes = "application/json")
    public CompletableFuture<StoreDao> updateProductsByBarcode(@PathVariable String storeName, @RequestBody Store store) throws Exception {
    		Store existingStore = this.storeQueryService.findByStoreName(storeName);
    		existingStore.setStoreName(store.getStoreName());
    		existingStore.setWebsite(store.getWebsite());
    		return this.storeCommandService
    				.update(this.storeQueryService.findByStoreName(storeName).getId(), existingStore)
    				.thenApply(entity -> new StoreDao(entity.getEntityId(), entity.getAggregate().getStore()));
}
    
    @DeleteMapping(value = "/{storeName}")
    public CompletableFuture<ResponseEntity<?>> deleteStore(@NotBlank @PathVariable String storeName) {
        return this.storeCommandService
                .delete(this.storeQueryService.findByStoreName(storeName).getId())
                .thenApply(entity -> ResponseEntity.ok().build());
    }
}
