package org.cart.command.service.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.cart.command.service.aggregate.CartAggregate;
import org.cart.command.service.command.AddProductCommand;
import org.cart.command.service.command.CartCommand;
import org.cart.command.service.command.DeleteProductCommand;
import org.cart.command.service.command.UpdateProductQuantityCommand;
import org.cart.domain.service.model.Product;

import java.util.concurrent.CompletableFuture;

public class CommandService {

    private AggregateRepository<CartAggregate, CartCommand> aggregateRepository;

    public CommandService(AggregateRepository<CartAggregate, CartCommand> aggregateRepository) {
        this.aggregateRepository = aggregateRepository;
    }

    public CompletableFuture<EntityWithIdAndVersion<CartAggregate>> addProduct(Product product) {
        return this.aggregateRepository.save(new AddProductCommand(product));
    }

    public CompletableFuture<EntityWithIdAndVersion<CartAggregate>> updateProductQuantity(Product product) {
        return this.aggregateRepository.update(product.getId(), new UpdateProductQuantityCommand(product));
    }

    public CompletableFuture<EntityWithIdAndVersion<CartAggregate>> deleteProduct(Product product) {
        return this.aggregateRepository.update(product.getId(), new DeleteProductCommand());
    }
}
