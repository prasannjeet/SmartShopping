package org.product.command.service.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.product.command.service.aggregate.ProductAggregate;
import org.product.command.service.aggregate.ProductBulkDeleteAggregate;
import org.product.command.service.command.*;
import org.product.domain.service.model.Product;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ProductCommandService {

    private final AggregateRepository<ProductAggregate, ProductCommand> productAggregateRepository;
    private final AggregateRepository<ProductBulkDeleteAggregate, ProductCommand> productBulkDeleteAggregateRepository;

    public ProductCommandService(
            AggregateRepository<ProductAggregate, ProductCommand> productAggregateRepository,
            AggregateRepository<ProductBulkDeleteAggregate, ProductCommand> productBulkDeleteAggregateRepository) {
        this.productAggregateRepository = productAggregateRepository;
        this.productBulkDeleteAggregateRepository = productBulkDeleteAggregateRepository;
    }

    public CompletableFuture<EntityWithIdAndVersion<ProductAggregate>> save(Product product) {
        return this.productAggregateRepository.save(new CreateProductCommand(product));
    }

    public CompletableFuture<EntityWithIdAndVersion<ProductAggregate>> update(Product product) {
        return this.productAggregateRepository.update(product.getId(), new UpdateProductCommand(product.getId(), product));
    }

    public CompletableFuture<EntityWithIdAndVersion<ProductAggregate>> delete(String id) {
        return this.productAggregateRepository.update(id, new DeleteProductCommand());
    }

    public CompletableFuture<EntityWithIdAndVersion<ProductBulkDeleteAggregate>> deleteAll(List<String> ids) {
        return this.productBulkDeleteAggregateRepository.save(new DeleteProductsCommand(ids));
    }
}