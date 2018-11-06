package org.store.domain.event;

import org.store.domain.model.Product;

public class StoreEventProductCreated implements StoreEvent {

    private Product product;

    public StoreEventProductCreated() {

    }

    public StoreEventProductCreated(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
