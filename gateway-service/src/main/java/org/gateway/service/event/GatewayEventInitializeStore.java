package org.gateway.service.event;

import org.gateway.service.model.StoreInfos;

public class GatewayEventInitializeStore implements GatewayEvent {

    private StoreInfos storeInfos; 

    public GatewayEventInitializeStore() {

    }

    public GatewayEventInitializeStore(StoreInfos storeInfos) {
        this.storeInfos = storeInfos;
    }

    public StoreInfos getStoreInfos() {
        return this.storeInfos;
    }

    public void setStoreInfos(StoreInfos storeInfos) {
        this.storeInfos = storeInfos;
    }

}