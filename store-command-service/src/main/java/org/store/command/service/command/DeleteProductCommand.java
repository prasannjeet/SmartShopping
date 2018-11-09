package org.store.command.service.command;

import org.store.domain.model.PriceTag;
import org.store.domain.model.Store;

public class DeleteProductCommand implements StoreCommand {

    private Store store;
    private PriceTag priceTag;

    public DeleteProductCommand(PriceTag priceTag) {
        this.priceTag = priceTag;
    }

    public PriceTag getPriceTag() {
        return this.priceTag;
    }

    public Store getStore() {
        return this.store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}