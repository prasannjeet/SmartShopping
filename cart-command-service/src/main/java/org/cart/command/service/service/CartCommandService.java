package org.cart.command.service.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.cart.command.service.aggregate.CartAggregate;
import org.cart.command.service.aggregate.CartBulkDeleteAggregate;
import org.cart.command.service.command.CartCommand;
import org.cart.command.service.command.CreateCartCommand;
import org.cart.command.service.command.DeleteCartCommand;
import org.cart.command.service.command.DeleteCartsCommand;
import org.cart.command.service.command.UpdateCartCommand;
import org.cart.domain.service.model.Cart;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;

public class CartCommandService {

	private final AggregateRepository<CartAggregate, CartCommand> aggregateRepository;
	private final AggregateRepository<CartBulkDeleteAggregate, CartCommand> bulkDeleteAggregateRepository;

	public CartCommandService(AggregateRepository<CartAggregate, CartCommand> cartRepository,
			AggregateRepository<CartBulkDeleteAggregate, CartCommand> bulkDeleteAggregateRepository) {
		this.aggregateRepository = cartRepository;
		this.bulkDeleteAggregateRepository = bulkDeleteAggregateRepository;
	}

	public CompletableFuture<EntityWithIdAndVersion<CartAggregate>> create(Cart cart) {
		return this.aggregateRepository.save(new CreateCartCommand(cart));
	}

	public CompletableFuture<EntityWithIdAndVersion<CartAggregate>> remove(String id) {
		return this.aggregateRepository.update(id, new DeleteCartCommand());
	}

	public CompletableFuture<EntityWithIdAndVersion<CartAggregate>> update(String id, Cart cart) {
		return this.aggregateRepository.update(id, new UpdateCartCommand(id, cart));
	}

	public CompletableFuture<EntityWithIdAndVersion<CartBulkDeleteAggregate>> deleteAll(List<String> ids) {
		return this.bulkDeleteAggregateRepository.save(new DeleteCartsCommand(ids));
	}
}
