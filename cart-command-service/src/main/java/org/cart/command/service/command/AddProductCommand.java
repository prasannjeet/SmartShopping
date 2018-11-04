package org.cart.command.service.command;

import org.cart.domain.model.Product;

public class AddProductCommand implements CartCommand {

    private Product product;

    public AddProductCommand(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }
}
