package org.gateway.command.service.subscriber;

import java.util.concurrent.Semaphore;

import org.gateway.domain.event.GatewayEventInitializeStore;
import org.store.domain.event.StoreEventProductCreated;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

@EventSubscriber(id = "gatewayEventHandler")
public class Subscriber {

	public Semaphore storeInitSemaphore = new Semaphore(0);
	
	@EventHandlerMethod
	public void initializedStore(DispatchedEvent<GatewayEventInitializeStore> event){
		storeInitSemaphore.release();
	}
	
	@EventHandlerMethod
	public void addProductReponse(DispatchedEvent<StoreEventProductCreated> event){
		storeInitSemaphore.release();
	}
	
	@EventHandlerMethod
	public void updateProductReponse(DispatchedEvent<GatewayEventInitializeStore> event){
		storeInitSemaphore.release();
	}
	
	@EventHandlerMethod
	public void addStoreReponse(DispatchedEvent<GatewayEventInitializeStore> event){
		storeInitSemaphore.release();
	}
}
