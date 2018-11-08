package org.gateway.command.service.service;

import java.util.concurrent.CompletableFuture;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;

import org.gateway.command.service.aggregate.GatewayAggregate;
import org.gateway.command.service.command.AddProductInStoreCommand;
import org.gateway.command.service.command.GatewayCommand;
import org.gateway.command.service.command.InitiateStoreCommand;
import org.gateway.command.service.command.UpdatePriceInStoreCommand;
import org.gateway.domain.model.Product;
import org.gateway.domain.model.StoreInfos;

public class CommandService {

    private AggregateRepository<GatewayAggregate, GatewayCommand> aggregateRepository;

    public CommandService(AggregateRepository<GatewayAggregate, GatewayCommand> aggregateRepository) {
        this.aggregateRepository = aggregateRepository;
    }

	public CompletableFuture<EntityWithIdAndVersion<GatewayAggregate>> initStore(StoreInfos storeInfo) {
		return aggregateRepository.save(new InitiateStoreCommand(storeInfo));
	}

	public CompletableFuture<EntityWithIdAndVersion<GatewayAggregate>> addProductToStore(String storeId, Product product) {
		return aggregateRepository.save(new AddProductInStoreCommand(product, storeId));
	}

	public CompletableFuture<EntityWithIdAndVersion<GatewayAggregate>> updateProductInStore(String storeId, Product product) {
		return aggregateRepository.save(new UpdatePriceInStoreCommand(product, storeId));
	}
}
