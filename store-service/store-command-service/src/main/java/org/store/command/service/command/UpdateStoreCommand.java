package org.store.command.service.command;

import org.store.domain.service.model.Store;

public class UpdateStoreCommand implements StoreCommand {

    private String id;
    private Store store;

    public UpdateStoreCommand(String id, Store store) {
        this.id = id;
        this.store = store;
    }

    public String getId() {
        return this.id;
    }

    public Store getStore() {
        return this.store;
    }
}
