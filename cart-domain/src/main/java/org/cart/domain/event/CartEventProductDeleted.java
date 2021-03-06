package org.cart.domain.event;

import org.cart.domain.model.Product;

public class CartEventProductDeleted implements CartEvent {

    private Product product;

    public CartEventProductDeleted() {

    }

    public CartEventProductDeleted(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
