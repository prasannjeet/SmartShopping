package org.gateway.domain.event;

import org.gateway.domain.model.StoreInfos;

public class GatewayEventScrap implements GatewayEvent {

    private StoreInfos storeInfos;

    public GatewayEventScrap() {
    }

    public GatewayEventScrap(StoreInfos storeInfos) {
        this.storeInfos = storeInfos;
    }

    public StoreInfos getStoreInfos() {
        return this.storeInfos;
    }

    public void setStoreInfos(StoreInfos storeInfos) {
        this.storeInfos = storeInfos;
    }

}
