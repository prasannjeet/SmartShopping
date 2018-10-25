package org.cart.command.service.command;

import org.cart.domain.service.model.Cart;

public class CreateCartCommand implements CartCommand {

    private Cart cart;

    public CreateCartCommand(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return this.cart;
    }
}
