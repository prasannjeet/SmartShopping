package org.store.domain.event;

import org.store.domain.model.Store;

public class StoreEventStoreCreated implements StoreEvent {

    private Store store;

    public StoreEventStoreCreated() {

    }

    public StoreEventStoreCreated(Store store) {
        this.store = store;
    }

    public Store getStore() {
        return this.store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
