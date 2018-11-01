package org.product.command.service.controller;

import org.product.command.service.service.ProductCommandService;
import org.product.command.service.service.ProductQueryService;
import org.product.domain.service.model.Product;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/products", produces = "application/json")
@ResponseBody
public class Controller {

    private ProductCommandService productCommandService;
    private ProductQueryService productQueryService;

    public Controller(ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseBody
    public CompletableFuture<Product> create(@RequestBody @Valid Product product) throws Exception {
    		if(this.productQueryService.isDuplicate(product.getBarcode())) {
    			throw new Exception("Duplicate barcode = " + product.getBarcode());
    		}
    		return this.productCommandService
    				.save(product)
                .thenApply(entity -> new Product(entity.getEntityId(), entity.getAggregate().getProduct()));
    }
    
    @PutMapping(value = "/{barcode}", consumes = "application/json")
    public CompletableFuture<Product> updateProductsByBarcode(@PathVariable int barcode, @RequestBody Product product) throws Exception {
    		Product prod = this.productQueryService.findByBarcode(barcode);
    		prod.setName(product.getName());
    		prod.setBrand(product.getBrand());
    		return this.productCommandService
    				.update(prod)
    				.thenApply(entity -> new Product(entity.getEntityId(), entity.getAggregate().getProduct()));
    }

    @DeleteMapping(value = "/{barcode}")
    @ResponseBody
    public CompletableFuture<Product> deleteByBarcode(@PathVariable int barcode) {
        return this.productCommandService
        		.delete(this.productQueryService.findByBarcode(barcode).getId())
        		.thenApply(entity -> new Product(entity.getEntityId(), entity.getAggregate().getProduct()));
    }
}