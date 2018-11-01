package org.product.query.service.controller;

import org.product.domain.service.model.Product;
import org.product.query.service.service.ProductQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/products", produces = "application/json")
@ResponseBody
public class Controller {

    private ProductQueryService productQueryService;

    public Controller(ProductQueryService productQueryService) {
        this.productQueryService = productQueryService;
    }

    @GetMapping
    public CompletableFuture<List<Product>> findAll() {
        return CompletableFuture
                .supplyAsync(() -> this.productQueryService.findAll());
    }

    @GetMapping("/{barcode}")
    public CompletableFuture<Product> findByBarcode(@PathVariable int barcode) {
        return CompletableFuture
                .supplyAsync(() -> this.productQueryService.findByBarcode(barcode));
    }
}