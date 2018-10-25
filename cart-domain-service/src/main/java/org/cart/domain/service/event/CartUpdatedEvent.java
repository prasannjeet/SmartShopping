package org.cart.domain.service.event;

import org.cart.domain.service.model.Cart;

public class CartUpdatedEvent implements CartEvent {

    private Cart cart;

    public CartUpdatedEvent() {

    }

    public CartUpdatedEvent(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return this.cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
