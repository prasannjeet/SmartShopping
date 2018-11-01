package org.cart.domain.service.event;

import org.cart.domain.service.model.Product;

public class CartEventProductAdded implements CartEvent {

    private Product product;

    public CartEventProductAdded() {

    }

    public CartEventProductAdded(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
