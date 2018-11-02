package org.cart.command.service.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.cart.command.service.aggregate.CartAggregate;
import org.cart.command.service.command.AddProductCommand;
import org.cart.command.service.command.CartCommand;
import org.cart.command.service.command.DeleteProductCommand;
import org.cart.command.service.command.UpdateProductQuantityCommand;
import org.cart.domain.model.Product;
import org.cart.domain.repository.CartRepository;
import org.cart.domain.repository.ProductRepository;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class CommandService {

    private AggregateRepository<CartAggregate, CartCommand> aggregateRepository;
    private ProductRepository productRepository;
    private CartRepository cartRepository;

    public CommandService(AggregateRepository<CartAggregate, CartCommand> aggregateRepository,
                          ProductRepository productRepository, CartRepository cartRepository) {
        this.aggregateRepository = aggregateRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public CompletableFuture<EntityWithIdAndVersion<CartAggregate>> addProduct(Product product) throws Exception {
        if (this.cartRepository.findByUserId(product.getUserId()) == null) {
            throw new Exception("No cart with userId = " + product.getUserId());
        }
        if (this.productRepository.isDuplicate(product)) {
            throw new Exception("Cart already has a product with barcode = " + product.getBarcode());
        }
        return this.aggregateRepository.save(new AddProductCommand(product));
    }

    public CompletableFuture<EntityWithIdAndVersion<CartAggregate>> updateProductQuantity(Product product) {
        return this.aggregateRepository.update(product.getId(), new UpdateProductQuantityCommand(product));
    }

    public CompletableFuture<EntityWithIdAndVersion<CartAggregate>> deleteProduct(String userId, String barcode) {
        Product product = Optional
                .of(this.productRepository.findByBarcodeAndUserId(barcode, userId))
                .orElseThrow(() ->
                        new NoSuchElementException("No product with barcode = " + barcode + ", userId = " + userId));
        return this.aggregateRepository.update(product.getId(), new DeleteProductCommand(product));
    }
}
