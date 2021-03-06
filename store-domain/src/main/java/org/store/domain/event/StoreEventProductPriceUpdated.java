package org.store.domain.event;

import org.store.domain.model.PriceTag;
import org.store.domain.model.Store;

public class StoreEventProductPriceUpdated implements StoreEvent {

    private Store store;
    private PriceTag priceTag;

    public StoreEventProductPriceUpdated() {

    }

    public StoreEventProductPriceUpdated(Store store, PriceTag priceTag) {
        this.store = store;
        this.priceTag = priceTag;
    }

    public PriceTag getPriceTag() {
        return this.priceTag;
    }

    public void setPriceTag(PriceTag priceTag) {
        this.priceTag = priceTag;
    }

    public Store getStore() {
        return this.store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
