package org.gateway.domain.event;

import org.gateway.domain.model.StoreInfos;

public class GatewayEventUpdatePriceInStore implements GatewayEvent {

    private StoreInfos storeInfos;
    private String barcode;
    private String price;

    public GatewayEventUpdatePriceInStore() {

    }

    public GatewayEventUpdatePriceInStore(StoreInfos storeInfos, String barcode, String price) {
        this.storeInfos = storeInfos;
        this.barcode = barcode;
        this.price = price;
    }

    public StoreInfos getStoreInfos() {
        return this.storeInfos;
    }

    public void setStoreInfos(StoreInfos storeInfos) {
        this.storeInfos = storeInfos;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setSBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getPrice() {
        return this.price;
    }

    public void setSPrice(String price) {
        this.price = price;
    }

}