package org.gateway.command.service.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;

import org.gateway.command.service.aggregate.GatewayAggregate;
import org.gateway.command.service.command.AddProductInStoreCommand;
import org.gateway.command.service.command.GatewayCommand;
import org.gateway.command.service.command.InitiateStoreCommand;
import org.gateway.command.service.command.ScrapProductCommand;
import org.gateway.command.service.command.UpdatePriceInStoreCommand;
import org.gateway.command.service.subscriber.Subscriber;
import org.gateway.domain.model.Product;
import org.gateway.domain.model.StoreInfos;

public class CommandService {

    private AggregateRepository<GatewayAggregate, GatewayCommand> aggregateRepository;
    private Subscriber subscriber;
    
    private int timeout = 5000;
    
    public CommandService(AggregateRepository<GatewayAggregate, GatewayCommand> aggregateRepository, Subscriber subscriber) {
        this.aggregateRepository = aggregateRepository;
        this.subscriber = subscriber;
    }

	public StoreInfos initStore(StoreInfos storeInfo){
		aggregateRepository.save(new InitiateStoreCommand(storeInfo));
		boolean responseCatched = false;
		
		try {
			responseCatched = subscriber.storeInitSemaphore.tryAcquire(timeout, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (responseCatched)
			return storeInfo;
		else return null;
	}

	public CompletableFuture<EntityWithIdAndVersion<GatewayAggregate>> addProductToStore(String storeId, Product product) {
		return aggregateRepository.save(new AddProductInStoreCommand(product, storeId));
	}

	public CompletableFuture<EntityWithIdAndVersion<GatewayAggregate>> updateProductInStore(String storeId, Product product) {
		return aggregateRepository.save(new UpdatePriceInStoreCommand(product, storeId));
	}
	
	public CompletableFuture<EntityWithIdAndVersion<GatewayAggregate>> scrapProduct(String storeId) {
		return aggregateRepository.save(new ScrapProductCommand(storeId));
	}
	
}
