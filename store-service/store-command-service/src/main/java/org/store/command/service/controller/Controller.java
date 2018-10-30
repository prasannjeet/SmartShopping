package org.store.command.service.controller;

import org.store.command.service.service.StoreCommandService;
import org.store.command.service.service.StoreQueryService;
import org.store.command.service.service.ProductCommandService;
import org.store.command.service.service.ProductQueryService;
import org.store.domain.service.dao.StoreDao;
import org.store.domain.service.model.Store;
import org.store.domain.service.model.Product;
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
    private ProductCommandService productCommandService;
    private ProductQueryService productQueryService;

    public Controller(StoreCommandService storeCommandService, StoreQueryService storeQueryService,
                      ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.storeCommandService = storeCommandService;
        this.storeQueryService = storeQueryService;
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }

    @PostMapping(consumes = "application/json")
    public CompletableFuture<StoreDao> createStore(@RequestBody @Valid Store store) throws Exception {
        if (this.storeQueryService.isDuplicate(store.getUserId())) {
            throw new Exception("Duplicate userId = " + store.getUserId());
        }
        return this.storeCommandService
                .save(store)
                .thenApply(entity -> new StoreDao(entity.getEntityId(), entity.getAggregate().getStore()));
    }

    @PostMapping(value = "/products", consumes = "application/json")
    public CompletableFuture<Product> addProductInStore(@RequestBody @Valid Product product) throws Exception {
        if (this.storeQueryService.findByUserId(product.getUserId()) == null) {
            throw new Exception("No store with userId = " + product.getUserId());
        }
        return this.productCommandService
                .save(product)
                .thenApply(entity -> new Product(entity.getEntityId(), entity.getAggregate().getProduct()));
    }

    @PutMapping(value = "/{userId}/products/{productId}/{quantity}")
    public CompletableFuture<Product> updateProductQuantityFromStore(@NotBlank @PathVariable String userId,
                                                                    @NotBlank @PathVariable String productId,
                                                                    @NotBlank @PathVariable String quantity) throws Exception {
        Product product = this.productQueryService.findByIdAndUserId(productId, userId);
        product.setQuantity(quantity);
        return this.productCommandService
                .update(product)
                .thenApply(entity -> new Product(entity.getEntityId(), entity.getAggregate().getProduct()));
    }

    @DeleteMapping(value = "/{userId}/products/{productId}")
    public CompletableFuture<Product> deleteProductFromStore(@NotBlank @PathVariable String userId,
                                                            @NotBlank @PathVariable String productId) {
        return this.productCommandService
                .delete(this.productQueryService.findByIdAndUserId(productId, userId).getId())
                .thenApply(entity -> new Product(entity.getEntityId(), entity.getAggregate().getProduct()));
    }

    @PutMapping(value = "/products/{barcode}", consumes = "application/json")
    public CompletableFuture<ResponseEntity<?>> updateProductsByBarcode(@NotBlank @PathVariable String barcode,
                                                                        @RequestBody Product product) {
        List<Product> products = this.productQueryService.findByBarcode(barcode);
        List<CompletableFuture<?>> futures = new LinkedList<>();
        products.forEach(prod -> {
            prod.setName(product.getName());
            prod.setBrand(product.getBrand());
            futures.add(this.productCommandService.update(prod));
        });
        return CompletableFuture
                .allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .thenApply(entity -> ResponseEntity.ok().build());
    }

    @DeleteMapping(value = "/products/{barcode}")
    public CompletableFuture<ResponseEntity<?>> deleteProductsByBarcode(@NotBlank @PathVariable String barcode) {
        return this.productCommandService
                .deleteAll(this.productQueryService.getIdsByBarcode(barcode))
                .thenApply(entity -> ResponseEntity.ok().build());
    }
}
