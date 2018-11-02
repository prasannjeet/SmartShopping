package org.cart.domain.service.event;

import org.cart.domain.service.model.Product;

public class ProductAddedEvent implements ProductEvent {

    private Product product;

    public ProductAddedEvent() {

    }

    public ProductAddedEvent(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}