package org.cart.domain.event;

import org.cart.domain.dao.CartDaoForStore;

public class CartEventProductsPricesAsked implements CartEvent {

    private CartDaoForStore cart;

    public CartEventProductsPricesAsked() {

    }

    public CartEventProductsPricesAsked(CartDaoForStore cart) {
        this.cart = cart;
    }

    public CartDaoForStore getCart() {
        return this.cart;
    }

    public void setCart(CartDaoForStore cart) {
        this.cart = cart;
    }
}
