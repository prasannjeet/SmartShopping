package org.cart.command.service.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.cart.command.service.aggregate.CartAggregate;
import org.cart.command.service.aggregate.CartBulkDeleteAggregate;
import org.cart.command.service.command.*;
import org.cart.domain.service.model.Cart;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CartCommandService {

    private final AggregateRepository<CartAggregate, CartCommand> cartAggregateRepository;
    private final AggregateRepository<CartBulkDeleteAggregate, CartCommand> cartBulkDeleteAggregateRepository;

    public CartCommandService(
            AggregateRepository<CartAggregate, CartCommand> cartAggregateRepository,
            AggregateRepository<CartBulkDeleteAggregate, CartCommand> cartBulkDeleteAggregateRepository) {
        this.cartAggregateRepository = cartAggregateRepository;
        this.cartBulkDeleteAggregateRepository = cartBulkDeleteAggregateRepository;
    }

    public CompletableFuture<EntityWithIdAndVersion<CartAggregate>> create(Cart cart) {
        return this.cartAggregateRepository.save(new CreateCartCommand(cart));
    }

    public CompletableFuture<EntityWithIdAndVersion<CartAggregate>> update(String id, Cart cart) {
        return this.cartAggregateRepository.update(id, new UpdateCartCommand(id, cart));
    }

    public CompletableFuture<EntityWithIdAndVersion<CartAggregate>> delete(String id) {
        return this.cartAggregateRepository.update(id, new DeleteCartCommand());
    }

    public CompletableFuture<EntityWithIdAndVersion<CartBulkDeleteAggregate>> deleteAll(List<String> ids) {
        return this.cartBulkDeleteAggregateRepository.save(new DeleteCartsCommand(ids));
    }
}
