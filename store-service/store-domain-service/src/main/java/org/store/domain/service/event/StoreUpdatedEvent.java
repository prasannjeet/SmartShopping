package org.store.domain.service.event;

import org.store.domain.service.model.Store;

public class StoreUpdatedEvent implements StoreEvent {

    private Store store;

    public StoreUpdatedEvent() {

    }

    public StoreUpdatedEvent(Store store) {
        this.store = store;
    }

    public Store getStore() {
        return this.store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
