package org.gateway.command.service.service;

import io.eventuate.AggregateRepository;
import org.gateway.command.service.aggregate.GatewayAggregate;
import org.gateway.command.service.command.*;
import org.gateway.command.service.subscriber.Subscriber;
import org.gateway.domain.model.Product;
import org.gateway.domain.model.StoreInfos;
import org.store.domain.model.PriceTag;
import org.store.domain.model.Store;

import java.util.concurrent.TimeUnit;

public class CommandService {

    private AggregateRepository<GatewayAggregate, GatewayCommand> aggregateRepository;
    private Subscriber subscriber;

    private int timeout = 5000;

    public CommandService(AggregateRepository<GatewayAggregate, GatewayCommand> aggregateRepository, Subscriber subscriber) {
        this.aggregateRepository = aggregateRepository;
        this.subscriber = subscriber;
    }

    public Store initStore(StoreInfos storeInfo) throws Exception {
        aggregateRepository.save(new InitiateStoreCommand(storeInfo));

        boolean responseCatched = subscriber.storeInitSemaphore.tryAcquire(timeout, TimeUnit.MILLISECONDS);
        if (!responseCatched)
            throw new Exception("Timeout: Response not received in time (" + timeout + "ms)");
        return subscriber.storeInitEvent.getStore();
    }

    public org.store.domain.model.Product addProductToStore(String storeId, Product product) throws Exception {
        aggregateRepository.save(new AddProductInStoreCommand(product, storeId));

        boolean responseCatched = subscriber.addProductSemaphore.tryAcquire(timeout, TimeUnit.MILLISECONDS);
        if (!responseCatched)
            throw new Exception("Timeout: Response not received in time (" + timeout + "ms)");
        return subscriber.addProductEvent.getProduct();
    }

    public PriceTag updateProductInStore(String storeId, Product product) throws Exception {
        aggregateRepository.save(new UpdatePriceInStoreCommand(product, storeId));

        boolean responseCatched = subscriber.updateProductSemaphore.tryAcquire(timeout, TimeUnit.MILLISECONDS);
        if (!responseCatched)
            throw new Exception("Timeout: Response not received in time (" + timeout + "ms)");
        return subscriber.updateProductEvent.getPriceTag();
    }

    public Store scrapProduct(String storeId) throws Exception {
        aggregateRepository.save(new ScrapProductCommand(storeId));

        boolean responseCatched = subscriber.scrapStoreSemaphore.tryAcquire(timeout, TimeUnit.MILLISECONDS);
        if (!responseCatched)
            throw new Exception("Timeout: Response not received in time (" + timeout + "ms)");
        return subscriber.scrapStoreEvent.getStore();
    }
}