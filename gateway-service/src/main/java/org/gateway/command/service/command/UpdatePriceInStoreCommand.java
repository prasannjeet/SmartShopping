package org.gateway.command.service.command;

import org.gateway.domain.model.Product;

public class UpdatePriceInStoreCommand implements GatewayCommand {

    private Product product;
    private String storeId;

    public UpdatePriceInStoreCommand() {

    }

    public UpdatePriceInStoreCommand(Product product, String storeId) {
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
