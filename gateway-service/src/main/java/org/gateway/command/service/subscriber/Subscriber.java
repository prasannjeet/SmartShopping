package org.gateway.command.service.subscriber;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

import org.store.domain.event.StoreEventProductCreated;
import org.store.domain.event.StoreEventProductPriceUpdated;
import org.store.domain.event.StoreEventScrapperLaunched;
import org.store.domain.event.StoreEventStoreCreated;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

@EventSubscriber(id = "gatewayEventHandler")
public class Subscriber {

    public Semaphore storeInitSemaphore = new Semaphore(0);
    public Semaphore addProductSemaphore = new Semaphore(0);
    public Semaphore updateProductSemaphore = new Semaphore(0);
    public Semaphore scrapStoreSemaphore = new Semaphore(0);

    public StoreEventStoreCreated storeInitEvent = null;
    public StoreEventProductCreated addProductEvent = null;
    public StoreEventProductPriceUpdated updateProductEvent = null;
    public StoreEventScrapperLaunched scrapStoreEvent = null;
	
    private List<Object> subscribers = new ArrayList<Object>();

    @EventHandlerMethod
    public void initializedStoreResponse(DispatchedEvent<StoreEventStoreCreated> event) {
    	if (subscribers.isEmpty())
    		return;
    	storeInitEvent = event.getEvent();
        storeInitSemaphore.release();
    }

    @EventHandlerMethod
    public void updateProductReponse(DispatchedEvent<StoreEventProductPriceUpdated> event) {
    	if (subscribers.isEmpty())
    		return;
    	updateProductEvent = event.getEvent();
        updateProductSemaphore.release();
    }

    @EventHandlerMethod
    public void addProductReponse(DispatchedEvent<StoreEventProductCreated> event) {
    	if (subscribers.isEmpty())
    		return;
    	addProductEvent = event.getEvent();
        addProductSemaphore.release();
    }

    @EventHandlerMethod
    public void scrapStoreReponse(DispatchedEvent<StoreEventScrapperLaunched> event) {
    	if (subscribers.isEmpty())
    		return;
        scrapStoreEvent = event.getEvent();
        scrapStoreSemaphore.release();
    }
    
    public void subscribe(Object o){
    	subscribers.add(o);
    }
    
    public void unsubscribe(Object o){
    	subscribers.remove(o);
    }
}
