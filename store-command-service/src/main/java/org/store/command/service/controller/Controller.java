package org.store.command.service.controller;

import io.eventuate.EntityWithIdAndVersion;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;
import org.store.command.service.aggregate.PriceTagAggregate;
import org.store.command.service.service.CommandService;
import org.store.command.service.service.QueryService;
import org.store.domain.service.model.PriceTag;
import org.store.domain.service.model.Store;

import javax.validation.Valid;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/store", produces = "application/json")
@ResponseBody
public class Controller {

    private CommandService commandService;
    private QueryService queryService;

    public Controller(CommandService commandService, QueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping(value = "/prices", consumes = "application/json")
    public CompletableFuture<PriceTag> addPriceTag(@RequestBody @Valid PriceTag priceTag) throws Exception {
        return this.commandService
                .addPriceTag(priceTag)
                .thenApply(entity -> new PriceTag(entity.getEntityId(), entity.getAggregate().getPriceTag()));
    }

    @PutMapping(value = "/prices/{barcode}/{price}")
    public CompletableFuture<PriceTag> updatePriceTagQuantity(@NotBlank @PathVariable String barcode,
                                                              @NotBlank @PathVariable double price) throws Exception {
        return this.commandService
                .updatePriceTag(barcode, price)
                .thenApply(entity -> new PriceTag(entity.getEntityId(), entity.getAggregate().getPriceTag()));
    }

    @DeleteMapping(value = "/prices/{barcode}")
    public CompletableFuture<PriceTag> deletePriceTag(@NotBlank @PathVariable String barcode) {
        return this.commandService
                .deletePriceTag(barcode)
                .thenApply(entity -> new PriceTag(entity.getEntityId(), entity.getAggregate().getPriceTag()));
    }

    @PostMapping(consumes = "application/json")
    public CompletableFuture<Store> setStoreInfo(@RequestBody @Valid Store store) throws Exception {
        return CompletableFuture
                .supplyAsync(() -> this.queryService.setStoreInfo(store));
    }
    
    private PriceTag getPriceTag(EntityWithIdAndVersion<PriceTagAggregate> entity) {
        PriceTag priceTag = entity.getAggregate().getPriceTag();
        priceTag.setBarcode(entity.getEntityId());
        return priceTag;
    }
}
