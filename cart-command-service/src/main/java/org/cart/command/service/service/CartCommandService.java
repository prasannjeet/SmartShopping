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

    private final AggregateRepository<CartAggregate, CartCommand> aggregateRepository;
    private final AggregateRepository<CartBulkDeleteAggregate, CartCommand> bulkDeleteAggregateRepository;

    public CartCommandService(AggregateRepository<CartAggregate, CartCommand> aggregateRepository,
                              AggregateRepository<CartBulkDeleteAggregate, CartCommand> bulkDeleteAggregateRepository) {
        this.aggregateRepository = aggregateRepository;
        this.bulkDeleteAggregateRepository = bulkDeleteAggregateRepository;
    }

    public CompletableFuture<EntityWithIdAndVersion<CartAggregate>> save(Cart cart) {
        return this.aggregateRepository.save(new CreateCartCommand(cart));
    }

    public CompletableFuture<EntityWithIdAndVersion<CartAggregate>> update(String id, Cart cart) {
        return this.aggregateRepository.update(id, new UpdateCartCommand(id, cart));
    }

    public CompletableFuture<EntityWithIdAndVersion<CartAggregate>> delete(String id) {
        return this.aggregateRepository.update(id, new DeleteCartCommand());
    }

    public CompletableFuture<EntityWithIdAndVersion<CartBulkDeleteAggregate>> deleteAll(List<String> ids) {
        return this.bulkDeleteAggregateRepository.save(new DeleteCartsCommand(ids));
    }
}
