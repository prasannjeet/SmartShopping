package org.cart.command.service.controller;

import io.eventuate.EntityWithIdAndVersion;
import org.cart.command.service.aggregate.CartAggregate;
import org.cart.command.service.service.CommandService;
import org.cart.domain.dao.CartDao;
import org.cart.domain.model.Product;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/carts", produces = "application/json")
@ResponseBody
public class Controller {

    private CommandService commandService;

    public Controller(CommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping(value = "/products", consumes = "application/json")
    public CompletableFuture<Product> addProduct(@RequestBody @Valid Product product) throws Exception {
        return this.commandService
                .addProduct(product)
                .thenApply(entity -> this.getProduct(entity));
    }

    @PutMapping(value = "/products", consumes = "application/json")
    public CompletableFuture<Product> updateProductQuantity(@RequestBody @Valid Product product) throws Exception {
        return this.commandService
                .updateProductQuantity(product)
                .thenApply(entity -> this.getProduct(entity));
    }

    @DeleteMapping(value = "/{userId}/products/{barcode}")
    public CompletableFuture<Product> deleteProduct(@NotBlank @PathVariable String userId,
                                                    @NotBlank @PathVariable String barcode) {
        return this.commandService
                .deleteProduct(userId, barcode)
                .thenApply(entity -> this.getProduct(entity));
    }

    @PutMapping("/sort/stores-distance")
    public synchronized CompletableFuture<?> sortByStoresDistance(@RequestBody @Valid CartDao cartDao) throws Exception {
        return this.commandService
                .getProductsPrices(cartDao)
                .thenApply(res -> this.commandService.sortByStoresDistance());
    }

    @PutMapping("/sort/products-price")
    public synchronized CompletableFuture<?> sortByProductsPrice(@RequestBody @Valid CartDao cartDao) throws Exception {
        return this.commandService
                .getProductsPrices(cartDao)
                .thenApply(res -> this.commandService.sortByProductsPrice(cartDao.getUserId()));
    }

    private Product getProduct(EntityWithIdAndVersion<CartAggregate> entity) {
        Product product = entity.getAggregate().getProduct();
        product.setId(entity.getEntityId());
        return product;
    }
}
