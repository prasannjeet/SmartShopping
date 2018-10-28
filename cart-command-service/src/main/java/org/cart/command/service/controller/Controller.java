package org.cart.command.service.controller;

import org.cart.command.service.service.CartCommandService;
import org.cart.command.service.service.CartQueryService;
import org.cart.command.service.service.ProductCommandService;
import org.cart.command.service.service.ProductQueryService;
import org.cart.domain.service.dao.CartDao;
import org.cart.domain.service.model.Cart;
import org.cart.domain.service.model.Product;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public CompletableFuture<CartDao> save(@RequestBody @Valid Cart cart) {
        return this.cartCommandService.save(cart)
                .thenApply(entity -> new CartDao(entity.getEntityId(), entity.getAggregate().getCart()));
    }

    @PostMapping(value = "/products", consumes = "application/json")
    public CompletableFuture<Product> addProduct(@RequestBody @Valid Product product) {
        return this.productCommandService.save(product)
                .thenApply(entity -> new Product(entity.getEntityId(), entity.getAggregate().getProduct()));
    }

    @PutMapping(value = "/products", consumes = "application/json")
    public CompletableFuture<Product> updateProduct(@RequestBody @Valid Product product) {
        return this.productCommandService.update(product)
                .thenApply(entity -> new Product(entity.getEntityId(), entity.getAggregate().getProduct()));
    }

    @DeleteMapping(value = "/{userId}/products/{productId}")
    public CompletableFuture<CartDao> updateProduct(@NotBlank @PathVariable String userId,
                                                    @NotBlank @PathVariable String productId) {
        return this.productCommandService.delete(productId)
                .thenApply(entity -> new CartDao(
                        this.cartQueryService.findByUserId(userId),
                        this.productQueryService.findByUserId(userId)
                ));
    }
}
