package org.store.domain.event;

import org.store.domain.dao.StoreCartDao;

public class StoreEventCartUpdated implements StoreEvent {

    private StoreCartDao storeCartDao;

    public StoreEventCartUpdated() {

    }

    public StoreEventCartUpdated(StoreCartDao storeCartDao) {
        this.storeCartDao = storeCartDao;
    }

    public StoreCartDao getStoreCartDao() {
        return this.storeCartDao;
    }

    public void setStoreCartDao(StoreCartDao storeCartDao) {
        this.storeCartDao = storeCartDao;
    }
}