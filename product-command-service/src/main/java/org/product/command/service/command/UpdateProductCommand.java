package org.product.command.service.command;

import org.product.domain.service.model.Product;

public class UpdateProductCommand implements ProductCommand {

    private String id;
    private Product product;

    public UpdateProductCommand(String id, Product product) {
        this.id = id;
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public Product getProduct() {
        return this.product;
    }
}