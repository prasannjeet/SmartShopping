package org.store.command.service.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.store.command.service.aggregate.StoreAggregate;
import org.store.command.service.aggregate.StoreBulkDeleteAggregate;
import org.store.command.service.command.*;
import org.store.domain.service.model.Store;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class StoreCommandService {

    private final AggregateRepository<StoreAggregate, StoreCommand> aggregateRepository;
    private final AggregateRepository<StoreBulkDeleteAggregate, StoreCommand> bulkDeleteAggregateRepository;

    public StoreCommandService(AggregateRepository<StoreAggregate, StoreCommand> aggregateRepository,
                              AggregateRepository<StoreBulkDeleteAggregate, StoreCommand> bulkDeleteAggregateRepository) {
        this.aggregateRepository = aggregateRepository;
        this.bulkDeleteAggregateRepository = bulkDeleteAggregateRepository;
    }

    public CompletableFuture<EntityWithIdAndVersion<StoreAggregate>> save(Store store) {
        return this.aggregateRepository.save(new CreateStoreCommand(store));
    }

    public CompletableFuture<EntityWithIdAndVersion<StoreAggregate>> update(String id, Store store) {
        return this.aggregateRepository.update(id, new UpdateStoreCommand(id, store));
    }

    public CompletableFuture<EntityWithIdAndVersion<StoreAggregate>> delete(String id) {
        return this.aggregateRepository.update(id, new DeleteStoreCommand());
    }

    public CompletableFuture<EntityWithIdAndVersion<StoreBulkDeleteAggregate>> deleteAll(List<String> ids) {
        return this.bulkDeleteAggregateRepository.save(new DeleteStoresCommand(ids));
    }
}
