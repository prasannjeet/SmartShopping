package org.cart.command.service.command;

import org.cart.domain.service.model.Cart;

public class UpdateCartCommand implements CartCommand {

	private String id;
	private Cart cart;

	public UpdateCartCommand(String id, Cart cart) {
		this.id = id;
		this.cart = cart;
	}

	public String getId() {
		return id;
	}

	public Cart getCart() {
		return this.cart;
	}
}
