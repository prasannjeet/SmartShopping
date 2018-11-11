package org.gateway.command.service.command;

import org.gateway.domain.model.Product;

public class AddProductInStoreCommand implements GatewayCommand {

    private Product product;
    private String storeId;

    public AddProductInStoreCommand() {

    }

    public AddProductInStoreCommand(Product product, String storeId) {
        this.product = product;
        this.storeId = storeId;
    }

    public Product getProduct() {
        return product;
    }

    public String getStoreId() {
        return storeId;
    }
}
