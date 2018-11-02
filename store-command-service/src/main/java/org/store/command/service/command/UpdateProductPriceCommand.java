package org.store.command.service.command;

import org.store.domain.model.PriceTag;

public class UpdateProductPriceCommand implements StoreCommand {

    private PriceTag priceTag;

    public UpdateProductPriceCommand(PriceTag priceTag) {
        this.priceTag = priceTag;
    }

    public PriceTag getPriceTag() {
        return this.priceTag;
    }
}
