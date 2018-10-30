package org.store.domain.service.event;

import org.store.domain.service.model.Product;

public class ProductCreatedEvent implements ProductEvent {

    private Product product;

    public ProductCreatedEvent() {

    }

    public ProductCreatedEvent(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
