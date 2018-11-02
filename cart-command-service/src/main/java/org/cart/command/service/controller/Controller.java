package org.cart.command.service.controller;

import io.eventuate.EntityWithIdAndVersion;
import org.cart.command.service.aggregate.CartAggregate;
import org.cart.command.service.service.CommandService;
import org.cart.domain.service.model.Product;
import org.cart.domain.service.repository.CartRepository;
import org.cart.domain.service.repository.ProductRepository;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/carts", produces = "application/json")
@ResponseBody
public class Controller {

    private CommandService commandService;
    private ProductRepository productRepository;
    private CartRepository cartRepository;

    public Controller(CommandService commandService, ProductRepository productRepository, CartRepository cartRepository) {
        this.commandService = commandService;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    @PostMapping(value = "/products", consumes = "application/json")
    public CompletableFuture<Product> addProduct(@RequestBody @Valid Product product) throws Exception {
        if (this.cartRepository.findByUserId(product.getUserId()) == null) {
            throw new Exception("No cart with userId = " + product.getUserId());
        }
        if (this.productRepository.isDuplicate(product)) {
            throw new Exception("Cart already has a product with barcode = " + product.getBarcode());
        }
        return this.commandService
                .addProduct(product)
                .thenApply(entity -> this.getProduct(entity));
    }

    @PutMapping(value = "/{userId}/products/{barcode}/{quantity}")
    public CompletableFuture<Product> updateProductQuantity(@NotBlank @PathVariable String userId,
                                                            @NotBlank @PathVariable String barcode,
                                                            @NotBlank @PathVariable String quantity) throws Exception {
        Product product = Optional
                .of(this.productRepository.findByBarcodeAndUserId(barcode, userId))
                .orElseThrow(() -> new NoSuchElementException("No product with barcode = " + barcode + ", userId = " + userId));
        product.setQuantity(quantity);
        return this.commandService
                .updateProductQuantity(product)
                .thenApply(entity -> this.getProduct(entity));
    }

    @DeleteMapping(value = "/{userId}/products/{barcode}")
    public CompletableFuture<Product> deleteProduct(@NotBlank @PathVariable String userId,
                                                    @NotBlank @PathVariable String barcode) {
        return this.commandService
                .deleteProduct(
                        Optional
                                .of(this.productRepository.findByBarcodeAndUserId(barcode, userId))
                                .orElseThrow(() -> new NoSuchElementException(
                                        "No product with barcode = " + barcode + ", userId = " + userId)
                                )
                )
                .thenApply(entity -> this.getProduct(entity));
    }

    private Product getProduct(EntityWithIdAndVersion<CartAggregate> entity) {
        Product product = entity.getAggregate().getProduct();
        product.setId(entity.getEntityId());
        return product;
    }
}
