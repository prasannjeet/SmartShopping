package org.cart.command.service.command;

import org.cart.domain.service.model.Product;

public class CreateProductCommand implements ProductCommand {

    private Product product;

    public CreateProductCommand(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }
}
