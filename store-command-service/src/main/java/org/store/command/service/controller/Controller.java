package org.store.command.service.controller;

import io.eventuate.EntityWithIdAndVersion;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;
import org.store.command.service.aggregate.StoreAggregate;
import org.store.command.service.service.CommandService;
import org.store.domain.model.Product;
import org.store.domain.model.Store;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/stores", produces = "application/json")
@ResponseBody
public class Controller {

    private CommandService commandService;

    public Controller(CommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping(consumes = "application/json")
    public CompletableFuture<Store> createStore(@RequestBody @Valid Store store) throws Exception {
        return this.commandService
                .createStore(store)
                .thenApply(entity -> this.getStore(entity));

    }

    @PostMapping(value = "/products", consumes = "application/json")
    public CompletableFuture<Product> createProduct(@RequestBody @Valid Product product) throws Exception {
        return this.commandService
                .createProduct(product)
                .thenApply(entity -> this.getProduct(entity));

    }

    @PutMapping(value = "/products/{barcode}/{price}")
    public CompletableFuture<Product> updateProductPrice(@NotBlank @PathVariable String barcode,
                                                         @NotBlank @PathVariable String price) throws Exception {
        return this.commandService
                .updateProductPrice(barcode, price)
                .thenApply(entity -> this.getProduct(entity));

    }

    private Product getProduct(EntityWithIdAndVersion<StoreAggregate> entity) {
        Product product = entity.getAggregate().getProduct();
        product.setId(entity.getEntityId());
        return product;
    }

    private Store getStore(EntityWithIdAndVersion<StoreAggregate> entity) {
        Store store = entity.getAggregate().getStore();
        store.setId(entity.getEntityId());
        return store;
    }
}
