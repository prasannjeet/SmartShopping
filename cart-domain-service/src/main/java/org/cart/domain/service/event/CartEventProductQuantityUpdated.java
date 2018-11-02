package org.cart.domain.service.event;

import org.cart.domain.service.model.Product;

public class CartEventProductQuantityUpdated implements CartEvent {

    private Product product;

    public CartEventProductQuantityUpdated() {

    }

    public CartEventProductQuantityUpdated(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
