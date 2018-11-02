package org.store.domain.event;

import org.store.domain.model.PriceTag;

public class StoreEventProductPriceUpdated implements StoreEvent {

    private PriceTag priceTag;

    public StoreEventProductPriceUpdated(PriceTag priceTag) {
        this.priceTag = priceTag;
    }

    public PriceTag getPriceTag() {
        return this.priceTag;
    }

    public void setPriceTag(PriceTag priceTag) {
        this.priceTag = priceTag;
    }
}
