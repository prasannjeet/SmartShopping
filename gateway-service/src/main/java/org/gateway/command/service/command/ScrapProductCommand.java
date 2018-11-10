package org.gateway.command.service.command;

public class ScrapProductCommand implements GatewayCommand {
    String storeId;

    public ScrapProductCommand() {

    }

    public ScrapProductCommand(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreId() {
        return this.storeId;
    }
}
