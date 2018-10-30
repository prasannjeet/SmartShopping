package org.store.domain.service.event;

import org.store.domain.service.model.Product;

public class ProductUpdatedEvent implements ProductEvent {

    private Product product;

    public ProductUpdatedEvent() {

    }

    public ProductUpdatedEvent(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
