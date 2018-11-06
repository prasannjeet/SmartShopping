package org.store.command.service.command;

import org.store.domain.dao.StoreCartDao;

public class UpdateCartCommand implements StoreCommand {

    private StoreCartDao storeCartDao;

    public UpdateCartCommand(StoreCartDao storeCartDao) {
        this.storeCartDao = storeCartDao;
    }

    public StoreCartDao getStoreCartDao() {
        return this.storeCartDao;
    }
}