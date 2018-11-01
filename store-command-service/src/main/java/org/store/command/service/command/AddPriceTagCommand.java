package org.store.command.service.command;

import org.store.domain.service.model.PriceTag;

public class AddPriceTagCommand implements PriceTagCommand {

    private PriceTag priceTag;

    public AddPriceTagCommand(PriceTag priceTag) {
        this.priceTag = priceTag;
    }

    public PriceTag getPriceTag() {
        return this.priceTag;
    }
}
