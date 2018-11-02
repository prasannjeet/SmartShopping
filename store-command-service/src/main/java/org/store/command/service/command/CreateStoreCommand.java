package org.store.command.service.command;

import org.store.domain.model.Store;

public class CreateStoreCommand implements StoreCommand {

    private Store store;

    public CreateStoreCommand(Store store) {
        this.store = store;
    }

    public Store getStore() {
        return this.store;
    }
}
