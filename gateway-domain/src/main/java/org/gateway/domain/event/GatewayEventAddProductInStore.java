package org.gateway.domain.event;

import org.gateway.domain.model.Product;
import org.gateway.domain.model.StoreInfos;

public class GatewayEventAddProductInStore implements GatewayEvent {

    private StoreInfos storeInfos;
    private Product product;

    public GatewayEventAddProductInStore() {

    }

    public GatewayEventAddProductInStore(StoreInfos storeInfos, Product product) {
        this.storeInfos = storeInfos;
        this.product = product;
    }

    public StoreInfos getStoreInfos() {
        return this.storeInfos;
    }

    public void setStoreInfos(StoreInfos storeInfos) {
        this.storeInfos = storeInfos;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}