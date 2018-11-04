package org.user.domain.event;

import org.user.domain.dao.StoreCartDao;

public class StoreEventCartUpdated implements UserEvent {

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
