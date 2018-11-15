package org.gateway.command.service.subscriber;

import java.util.List;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

import org.store.domain.event.StoreEventProductCreated;
import org.store.domain.event.StoreEventProductPriceUpdated;
import org.store.domain.event.StoreEventScrapperLaunched;
import org.store.domain.event.StoreEventStoreCreated;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

@EventSubscriber(id = "gatewayEventHandler")
public class Subscriber {
	    
    private MultivaluedMap<EventType, MessageListener> subscribers = new MultivaluedHashMap<EventType, MessageListener>();
    
    @EventHandlerMethod
    public void initializedStoreResponse(DispatchedEvent<StoreEventStoreCreated> event) {
    	List<MessageListener> listeners = subscribers.get(EventType.STORE_CREATED);
    	if (listeners == null || listeners.isEmpty())
    		return;
    	else
    		listeners.get(0).ReceiveEvent(event.getEvent());
    }

    @EventHandlerMethod
    public void updateProductReponse(DispatchedEvent<StoreEventProductPriceUpdated> event) {
    	List<MessageListener> listeners = subscribers.get(EventType.PRODUCT_UPDATED);
    	if (listeners == null || listeners.isEmpty())
    		return;
    	else
    		listeners.get(0).ReceiveEvent(event.getEvent());
    }

    @EventHandlerMethod
    public void addProductReponse(DispatchedEvent<StoreEventProductCreated> event) {
    	List<MessageListener> listeners = subscribers.get(EventType.PRODUCT_CREATED);
    	if (listeners == null || listeners.isEmpty())
    		return;
    	else
    		listeners.get(0).ReceiveEvent(event.getEvent());
    }

    @EventHandlerMethod
    public void scrapStoreReponse(DispatchedEvent<StoreEventScrapperLaunched> event) {
    	List<MessageListener> listeners = subscribers.get(EventType.SCRAPPER_LAUNCHED);
    	if (listeners == null || listeners.isEmpty())
    		return;
    	else
    		listeners.get(0).ReceiveEvent(event.getEvent());
    }
    
    public void subscribe(EventType eventType, MessageListener listener){
    	subscribers.add(eventType, listener);
    }
    
    public void unsubscribe(EventType eventType, MessageListener listener){
    	subscribers.remove(eventType, listener);
    }
}
