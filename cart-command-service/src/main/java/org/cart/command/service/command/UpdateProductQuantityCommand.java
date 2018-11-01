package org.cart.command.service.command;

import org.cart.domain.service.model.Product;

public class UpdateProductQuantityCommand implements CartCommand {

    private Product product;

    public UpdateProductQuantityCommand(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }
}
