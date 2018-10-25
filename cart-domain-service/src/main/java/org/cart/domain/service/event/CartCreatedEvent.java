package org.cart.domain.service.event;

import org.cart.domain.service.model.Cart;

public class CartCreatedEvent implements CartEvent {

    private Cart cart;

    public CartCreatedEvent() {

    }

    public CartCreatedEvent(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return this.cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
