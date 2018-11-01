package org.store.query.service.controller;

import org.store.domain.service.dao.PriceTagDao;
import org.store.query.service.service.StoreQueryService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/prices", produces = "application/json")
@ResponseBody
public class Controller {

    private StoreQueryService queryService;

    public Controller(StoreQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public CompletableFuture<List<PriceTagDao>> findAllPriceTags() {
        return CompletableFuture
                .supplyAsync(() -> this.queryService.findAllPriceTags());
    }

    @GetMapping("/{barcode}")
    public CompletableFuture<PriceTagDao> findPriceTagByBarcode(@PathVariable @NotBlank String barcode) {
        return CompletableFuture
                .supplyAsync(() -> this.queryService.findPriceTagByBarcode(barcode));
    }
}
