package org.gateway.command.service.subscriber;

import java.util.concurrent.Semaphore;

import org.gateway.domain.event.GatewayEventAddProductInStore;
import org.gateway.domain.event.GatewayEventInitializeStore;
import org.gateway.domain.event.GatewayEventScrap;
import org.gateway.domain.event.GatewayEventUpdatePriceInStore;
import org.store.domain.event.StoreEventProductCreated;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

@EventSubscriber(id = "gatewayEventHandler")
public class Subscriber {

	public Semaphore storeInitSemaphore = new Semaphore(0);
	public Semaphore addProductSemaphore = new Semaphore(0);
	public Semaphore updateProductSemaphore = new Semaphore(0);
	public Semaphore scrapStoreSemaphore = new Semaphore(0);
	
	@EventHandlerMethod
	public void initializedStore(DispatchedEvent<GatewayEventInitializeStore> event){
		storeInitSemaphore.release();
	}
	
	@EventHandlerMethod
	public void updateProductReponse(DispatchedEvent<GatewayEventUpdatePriceInStore> event){
		updateProductSemaphore.release();
	}
	
	@EventHandlerMethod
	public void addProductReponse(DispatchedEvent<GatewayEventAddProductInStore> event){
		addProductSemaphore.release();
	}
	
	/*
	@EventHandlerMethod
	public void addProductReponse(DispatchedEvent<StoreEventProductCreated> event){
		addProductSemaphore.release();
	}
	*/
	
	@EventHandlerMethod
	public void scrapStoreReponse(DispatchedEvent<GatewayEventScrap> event){
		scrapStoreSemaphore.release();
	}
}
