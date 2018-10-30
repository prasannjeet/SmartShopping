package org.store.command.service.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.store.command.service.aggregate.ProductAggregate;
import org.store.command.service.aggregate.ProductBulkDeleteAggregate;
import org.store.command.service.command.*;
import org.store.domain.service.model.Product;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ProductCommandService {

    private final AggregateRepository<ProductAggregate, ProductCommand> aggregateRepository;
    private final AggregateRepository<ProductBulkDeleteAggregate, ProductCommand> bulkDeleteAggregateRepository;

    public ProductCommandService(AggregateRepository<ProductAggregate, ProductCommand> aggregateRepository,
                                 AggregateRepository<ProductBulkDeleteAggregate, ProductCommand> bulkDeleteAggregateRepository) {
        this.aggregateRepository = aggregateRepository;
        this.bulkDeleteAggregateRepository = bulkDeleteAggregateRepository;
    }

    public CompletableFuture<EntityWithIdAndVersion<ProductAggregate>> save(Product product) {
        return this.aggregateRepository.save(new CreateProductCommand(product));
    }

    public CompletableFuture<EntityWithIdAndVersion<ProductAggregate>> update(Product product) {
        return this.aggregateRepository.update(product.getId(), new UpdateProductCommand(product.getId(), product));
    }

    public CompletableFuture<EntityWithIdAndVersion<ProductAggregate>> delete(String id) {
        return this.aggregateRepository.update(id, new DeleteProductCommand());
    }

    public CompletableFuture<EntityWithIdAndVersion<ProductBulkDeleteAggregate>> deleteAll(List<String> ids) {
        return this.bulkDeleteAggregateRepository.save(new DeleteProductsCommand(ids));
    }
}
