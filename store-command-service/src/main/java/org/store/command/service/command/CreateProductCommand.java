package org.store.command.service.command;

import org.store.domain.model.Product;
import org.store.domain.model.Store;

public class CreateProductCommand implements StoreCommand {

    private Store store;
    private Product product;

    public CreateProductCommand(Store store, Product product) {
        this.store = store;
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }

    public Store getStore() {
        return this.store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
