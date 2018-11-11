package org.store.domain.event;

import org.store.domain.model.Store;

public class StoreEventScrapperLaunched implements StoreEvent {

    private Store store;

    public StoreEventScrapperLaunched() {

    }

    public StoreEventScrapperLaunched(Store store) {
        this.store = store;
    }

    public Store getStore() {
        return this.store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

}