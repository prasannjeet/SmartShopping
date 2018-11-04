package org.cart.command.service.command;

import org.cart.domain.dao.CartDao;

public class SortCartCommand implements CartCommand {

    private CartDao cartDao;

    public SortCartCommand(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    public CartDao getCartDao() {
        return this.cartDao;
    }
}
