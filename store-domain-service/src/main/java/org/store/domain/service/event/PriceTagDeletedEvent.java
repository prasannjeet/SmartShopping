package org.store.domain.service.event;

import org.store.domain.service.model.PriceTag;

public class PriceTagDeletedEvent implements PriceTagEvent {
    private PriceTag priceTag;

    public PriceTagDeletedEvent() {

    }

    public PriceTagDeletedEvent(PriceTag priceTag) {
        this.priceTag = priceTag;
    }

    public PriceTag getPriceTag() {
        return this.priceTag;
    }

    public void setPriceTag(PriceTag priceTag) {
        this.priceTag = priceTag;
    }
}
