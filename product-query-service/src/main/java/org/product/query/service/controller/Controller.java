package org.product.query.service.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.product.domain.model.Product;
import org.product.query.service.service.QueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/products", produces = "application/json")
@ResponseBody
public class Controller {

    private QueryService queryService;

    public Controller(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public CompletableFuture<List<Product>> findAll() {
        return CompletableFuture
                .supplyAsync(() -> this.queryService.findAll());
    }

    @GetMapping("/{barcode}")
    public CompletableFuture<Product> findByBarcode(@NotBlank @PathVariable String barcode) {
        return CompletableFuture
                .supplyAsync(() -> this.queryService.findByBarcode(barcode));
    }
}