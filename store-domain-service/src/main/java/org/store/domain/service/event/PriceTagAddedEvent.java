package org.store.domain.service.event;

import org.store.domain.service.model.PriceTag;

public class PriceTagAddedEvent implements PriceTagEvent {

    private PriceTag priceTag;

    public PriceTagAddedEvent() {

    }

    public PriceTagAddedEvent(PriceTag priceTag) {
        this.priceTag = priceTag;
    }

    public PriceTag getPriceTag() {
        return this.priceTag;
    }

    public void setPriceTag(PriceTag priceTag) {
        this.priceTag = priceTag;
    }
}
