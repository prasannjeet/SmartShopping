package org.store.domain.service.event;

import org.store.domain.service.model.PriceTag;

public class PriceTagUpdatedEvent implements PriceTagEvent {

    private PriceTag priceTag;

    public PriceTagUpdatedEvent() {

    }

    public PriceTagUpdatedEvent(PriceTag priceTag) {
        this.priceTag = priceTag;
    }

    public PriceTag getPriceTag() {
        return this.priceTag;
    }

    public void setPriceTag(PriceTag priceTag) {
        this.priceTag = priceTag;
    }
}
