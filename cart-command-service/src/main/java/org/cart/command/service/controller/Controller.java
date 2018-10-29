package org.cart.command.service.controller;

import org.cart.command.service.service.CartCommandService;
import org.cart.command.service.service.CartQueryService;
import org.cart.command.service.service.ProductCommandService;
import org.cart.command.service.service.ProductQueryService;
import org.cart.domain.service.dao.CartDao;
import org.cart.domain.service.model.Cart;
import org.cart.domain.service.model.Product;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/carts", produces = "application/json")
@ResponseBody
public class Controller {

    private CartCommandService cartCommandService;
    private CartQueryService cartQueryService;
    private ProductCommandService productCommandService;
    private ProductQueryService productQueryService;

    public Controller(CartCommandService cartCommandService, CartQueryService cartQueryService,
                      ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.cartCommandService = cartCommandService;
        this.cartQueryService = cartQueryService;
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }

    @PostMapping(consumes = "application/json")
    public CompletableFuture<CartDao> createCart(@RequestBody @Valid Cart cart) throws Exception {
        if (this.cartQueryService.isDuplicate(cart.getUserId())) {
            throw new Exception("Duplicate userId = " + cart.getUserId());
        }
        return this.cartCommandService
                .save(cart)
                .thenApply(entity -> new CartDao(entity.getEntityId(), entity.getAggregate().getCart()));
    }

    @PostMapping(value = "/products", consumes = "application/json")
    public CompletableFuture<Product> addProductInCart(@RequestBody @Valid Product product) throws Exception {
        if (this.cartQueryService.findByUserId(product.getUserId()) == null) {
            throw new Exception("No cart with userId = " + product.getUserId());
        }
        return this.productCommandService
                .save(product)
                .thenApply(entity -> new Product(entity.getEntityId(), entity.getAggregate().getProduct()));
    }

    @PutMapping(value = "/{userId}/products/{productId}/{quantity}")
    public CompletableFuture<Product> updateProductQuantityFromCart(@NotBlank @PathVariable String userId,
                                                                    @NotBlank @PathVariable String productId,
                                                                    @NotBlank @PathVariable String quantity) throws Exception {
        Product product = this.productQueryService.findByIdAndUserId(productId, userId);
        product.setQuantity(quantity);
        return this.productCommandService
                .update(product)
                .thenApply(entity -> new Product(entity.getEntityId(), entity.getAggregate().getProduct()));
    }

    @DeleteMapping(value = "/{userId}/products/{productId}")
    public CompletableFuture<Product> deleteProductFromCart(@NotBlank @PathVariable String userId,
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
