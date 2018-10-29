package org.store.query.service.subscriber;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.store.domain.service.event.StoreCreatedEvent;
import org.store.domain.service.event.StoreDeletedEvent;
import org.store.domain.service.event.StoreUpdatedEvent;
import org.store.domain.service.model.Store;
import org.store.query.service.service.StoreQueryService;

@EventSubscriber(id = "storeQueryEventHandlers")
public class StoreQueryEventSubscriber {

    private StoreQueryService storeQueryService;

    public StoreQueryEventSubscriber(StoreQueryService storeQueryService) {
        this.storeQueryService = storeQueryService;
    }

    @EventHandlerMethod
    public void create(DispatchedEvent<StoreCreatedEvent> dispatchedEvent) {
        Store store = new Store(dispatchedEvent.getEvent().getStore());
        store.setId(dispatchedEvent.getEntityId());
        this.storeQueryService.save(store);
    }

    @EventHandlerMethod
    public void update(DispatchedEvent<StoreUpdatedEvent> dispatchedEvent) {
        Store store = new Store(dispatchedEvent.getEvent().getStore());
        store.setId(dispatchedEvent.getEntityId());
        this.storeQueryService.save(store);

    }

    @EventHandlerMethod
    public void delete(DispatchedEvent<StoreDeletedEvent> dispatchedEvent) {
        this.storeQueryService.delete(dispatchedEvent.getEntityId());
    }
}
