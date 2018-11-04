package org.cart.domain.event;

import org.cart.domain.dao.CartDao;

public class CartEventSortingStarted implements CartEvent {

    private CartDao cartDao;

    public CartEventSortingStarted() {

    }

    public CartEventSortingStarted(CartDao cart) {
        this.cartDao = cart;
    }

    public CartDao getCartDao() {
        return this.cartDao;
    }

    public void setCartDao(CartDao cartDao) {
        this.cartDao = cartDao;
    }
}
