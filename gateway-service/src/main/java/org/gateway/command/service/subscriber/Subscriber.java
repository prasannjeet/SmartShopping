package org.gateway.command.service.subscriber;

import java.util.concurrent.Semaphore;

import org.store.domain.event.StoreEventProductCreated;
import org.store.domain.event.StoreEventProductPriceUpdated;
import org.store.domain.event.StoreEventScrapperLaunched;
import org.store.domain.event.StoreEventStoreCreated;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

@EventSubscriber(id = "gatewayEventHandler")
public class Subscriber {

	public Semaphore storeInitSemaphore = new Semaphore(0);
	public Semaphore addProductSemaphore = new Semaphore(0);
	public Semaphore updateProductSemaphore = new Semaphore(0);
	public Semaphore scrapStoreSemaphore = new Semaphore(0);
	
	public StoreEventStoreCreated storeInitEvent = null;
	public StoreEventProductPriceUpdated addProductEvent = null;
	public StoreEventProductCreated updateProductEvent = null;
	public StoreEventScrapperLaunched scrapStoreEvent = null;
	
	@EventHandlerMethod
	public void initializedStoreResponse(DispatchedEvent<StoreEventStoreCreated> event){
		storeInitSemaphore.release();
	}
	
	@EventHandlerMethod
	public void updateProductReponse(DispatchedEvent<StoreEventProductPriceUpdated> event){
		updateProductSemaphore.release();
	}
	
	@EventHandlerMethod
	public void addProductReponse(DispatchedEvent<StoreEventProductCreated> event){
		addProductSemaphore.release();
	}
	
	@EventHandlerMethod
	public void scrapStoreReponse(DispatchedEvent<StoreEventScrapperLaunched> event){
		scrapStoreSemaphore.release();
	}
}
