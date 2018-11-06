package org.cart.command.service.subscriber;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.store.domain.dao.StoreCartDao;
import org.store.domain.event.StoreEventCartUpdated;

import java.util.LinkedList;
import java.util.List;

@EventSubscriber(id = "cartCommandEventHandler")
public class CommandEventSubscriber {

    private List<StoreCartDao> storesCartsDaos;
    private boolean isSubscribing;

    public CommandEventSubscriber() {
        this.storesCartsDaos = new LinkedList<>();
        this.isSubscribing = false;
    }

    @EventHandlerMethod
    public void saveStoresCartsDaos(DispatchedEvent<StoreEventCartUpdated> event) {
        if (this.isSubscribing) {
            this.storesCartsDaos.add(event.getEvent().getStoreCartDao());
        }
    }

    public List<StoreCartDao> getStoresCartsDaos() {
        return this.storesCartsDaos;
    }

    public void subscribe() {
        this.storesCartsDaos.clear();
        this.isSubscribing = true;
    }

    public void unsubscribe() {
        this.isSubscribing = false;
    }

    public boolean isEmpty() {
        return this.storesCartsDaos.isEmpty();
    }
}
