package org.product.command.service.command;

import org.product.domain.service.model.Product;

public class CreateProductCommand implements ProductCommand {

    private Product product;

    public CreateProductCommand(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return this.product;
    }
}