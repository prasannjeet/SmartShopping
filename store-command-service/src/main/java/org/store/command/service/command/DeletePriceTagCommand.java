package org.store.command.service.command;

import org.store.domain.service.model.PriceTag;

public class DeletePriceTagCommand implements PriceTagCommand {
    private PriceTag priceTag;

    public DeletePriceTagCommand(PriceTag priceTag) {
        this.priceTag = priceTag;
    }

    public PriceTag getPriceTag() {
        return this.priceTag;
    }
}
