package org.store.command.service.command;

import org.store.domain.model.Store;

public class ScrapperInvokedCommand implements StoreCommand {

    private Store store;

    public ScrapperInvokedCommand(Store store) {
        this.store = store;
    }

    public Store getStore() {
        return this.store;
    }
}