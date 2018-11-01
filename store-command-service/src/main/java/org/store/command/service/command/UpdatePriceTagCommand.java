package org.store.command.service.command;

import org.store.domain.service.model.PriceTag;

public class UpdatePriceTagCommand implements PriceTagCommand {

    private String id;
    private PriceTag priceTag;

    public UpdatePriceTagCommand(String id, PriceTag priceTag) {
        this.id = id;
        this.priceTag = priceTag;
    }

    public String getId() {
        return this.id;
    }

    public PriceTag getPriceTag() {
        return this.priceTag;
    }
}
