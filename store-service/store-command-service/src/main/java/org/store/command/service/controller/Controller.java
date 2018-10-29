package org.store.command.service.controller;

import org.store.command.service.service.StoreCommandService;
import org.store.command.service.service.StoreQueryService;
import org.store.domain.service.dao.StoreDao;
import org.store.domain.service.model.Store;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        return this.storeCommandService
                .save(store)
                .thenApply(entity -> new StoreDao(entity.getEntityId(), entity.getAggregate().getStore()));
    }
}
