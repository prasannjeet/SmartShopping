package org.store.domain.event;

import org.store.domain.model.Product;
import org.store.domain.model.Store;

public class StoreEventProductCreated implements StoreEvent {

    private Store store;
    private Product product;

    public StoreEventProductCreated() {

    }

    public StoreEventProductCreated(Store store, Product product) {
        this.store = store;
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return this.store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
