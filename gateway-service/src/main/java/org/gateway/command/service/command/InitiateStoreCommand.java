package org.gateway.command.service.command;

import org.gateway.domain.model.StoreInfos;

public class InitiateStoreCommand implements GatewayCommand {
    StoreInfos storeInfo;

    public InitiateStoreCommand() {

    }

    public InitiateStoreCommand(StoreInfos storeInfo) {
        this.storeInfo = storeInfo;
    }

    public StoreInfos getstoreInfo() {
        return storeInfo;
    }
}
