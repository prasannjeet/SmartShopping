package org.store.command.service.command;

import org.store.domain.model.PriceTag;

public class DeleteProductCommand implements StoreCommand {

    private PriceTag priceTag;

    public DeleteProductCommand(PriceTag priceTag) {
        this.priceTag = priceTag;
    }

    public PriceTag getPriceTag() {
        return this.priceTag;
    }
}