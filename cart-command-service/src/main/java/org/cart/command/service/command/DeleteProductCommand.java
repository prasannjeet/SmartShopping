package org.cart.command.service.command;

import org.cart.domain.model.Product;

public class DeleteProductCommand implements CartCommand {

    private Product product;

    public DeleteProductCommand(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }
}
