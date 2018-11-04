package org.user.command.service.command;

import org.user.domain.dao.StoreCartDao;

public class UpdateCartCommand implements UserCommand {

    private StoreCartDao storeCartDao;

    public UpdateCartCommand(StoreCartDao storeCartDao) {
        this.storeCartDao = storeCartDao;
    }

    public StoreCartDao getStoreCartDao() {
        return this.storeCartDao;
    }
}
