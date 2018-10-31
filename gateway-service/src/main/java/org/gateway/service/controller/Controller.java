package org.gateway.service.controller;

import io.eventuate.EntityWithIdAndVersion;
import org.cart.command.service.aggregate.ProductAggregate;
import org.cart.command.service.service.CommandService;
import org.cart.domain.service.model.Product;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/", produces = "application/json")
@ResponseBody
public class Controller {

    private CommandService commandService;

    public Controller(CommandService commandService) {
        this.commandService = commandService;
    }
/*
    @PostMapping(value = "/products", consumes = "application/json")
    public CompletableFuture<Product> addProduct(@RequestBody @Valid Product product) throws Exception {
        return this.commandService
                .addProduct(product)
                .thenApply(entity -> entity.getAggregate().getProduct());
    }

    @PutMapping(value = "/{userId}/products/{barcode}/{quantity}")
    public CompletableFuture<Product> updateProductQuantity(@NotBlank @PathVariable String userId,
                                                            @NotBlank @PathVariable String barcode,
                                                            @NotBlank @PathVariable String quantity) throws Exception {
        return this.commandService
                .updateProductQuantity(barcode, userId, quantity)
                .thenApply(entity -> entity.getAggregate().getProduct());
    }

    @DeleteMapping(value = "/{userId}/products/{barcode}")
    public CompletableFuture<Product> deleteProduct(@NotBlank @PathVariable String userId,
                                                    @NotBlank @PathVariable String barcode) {
        return this.commandService
                .deleteProduct(barcode, userId)
                .thenApply(entity -> entity.getAggregate().getProduct());
    }

    private Product getProduct(EntityWithIdAndVersion<ProductAggregate> entity) {
        Product product = entity.getAggregate().getProduct();
        product.setBarcode(entity.getEntityId());
        return product;
    }
*/
}