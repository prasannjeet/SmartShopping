package org.gateway.command.service.service;

import io.eventuate.AggregateRepository;

import org.gateway.command.service.aggregate.GatewayAggregate;
import org.gateway.command.service.command.*;
import org.gateway.command.service.subscriber.EventType;
import org.gateway.command.service.subscriber.MessageListener;
import org.gateway.command.service.subscriber.Subscriber;
import org.gateway.domain.model.Product;
import org.gateway.domain.model.StoreInfos;
import org.store.domain.event.StoreEventProductCreated;
import org.store.domain.event.StoreEventProductPriceUpdated;
import org.store.domain.event.StoreEventScrapperLaunched;
import org.store.domain.event.StoreEventStoreCreated;
import org.store.domain.model.PriceTag;
import org.store.domain.model.Store;

public class CommandService {

    private AggregateRepository<GatewayAggregate, GatewayCommand> aggregateRepository;
    private Subscriber subscriber;

    private int timeout = 5000;

    public CommandService(AggregateRepository<GatewayAggregate, GatewayCommand> aggregateRepository, Subscriber subscriber) {
        this.aggregateRepository = aggregateRepository;
        this.subscriber = subscriber;
    }

    public Store initStore(StoreInfos storeInfo) throws Exception {
    	MessageListener<StoreEventStoreCreated> listener = new MessageListener<StoreEventStoreCreated>();
    	subscriber.subscribe(EventType.STORE_CREATED, listener);
    	
    	aggregateRepository.save(new InitiateStoreCommand(storeInfo));
    	StoreEventStoreCreated message = listener.WaitForMessage(timeout);
    	
        subscriber.unsubscribe(EventType.STORE_CREATED, listener);
        return message.getStore();
    }

    public org.store.domain.model.Product addProductToStore(String storeId, Product product) throws Exception {
    	MessageListener<StoreEventProductCreated> listener = new MessageListener<StoreEventProductCreated>();
    	subscriber.subscribe(EventType.PRODUCT_CREATED, listener);
    	
    	aggregateRepository.save(new AddProductInStoreCommand(product, storeId));
    	StoreEventProductCreated message = listener.WaitForMessage(timeout);
    	
        subscriber.unsubscribe(EventType.PRODUCT_CREATED, listener);
        return message.getProduct();
    }

    public PriceTag updateProductInStore(String storeId, Product product) throws Exception {
    	MessageListener<StoreEventProductPriceUpdated> listener = new MessageListener<StoreEventProductPriceUpdated>();
    	subscriber.subscribe(EventType.PRODUCT_UPDATED, listener);
    	
    	aggregateRepository.save(new UpdatePriceInStoreCommand(product, storeId));
    	StoreEventProductPriceUpdated message = listener.WaitForMessage(timeout);
    	
        subscriber.unsubscribe(EventType.PRODUCT_UPDATED, listener);
        return message.getPriceTag();
    }

    public Store scrapProduct(String storeId) throws Exception {
    	MessageListener<StoreEventScrapperLaunched> listener = new MessageListener<StoreEventScrapperLaunched>();
    	subscriber.subscribe(EventType.SCRAPPER_LAUNCHED, listener);
    	
    	aggregateRepository.save(new ScrapProductCommand(storeId));
    	StoreEventScrapperLaunched message = listener.WaitForMessage(timeout);
    	
        subscriber.unsubscribe(EventType.SCRAPPER_LAUNCHED, listener);
        return message.getStore();
        
        
    }
}