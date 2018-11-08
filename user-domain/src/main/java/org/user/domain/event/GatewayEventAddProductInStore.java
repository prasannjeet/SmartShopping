package org.user.domain.event;

import org.user.domain.model.StoreInfos;
import org.user.domain.model.Product;

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