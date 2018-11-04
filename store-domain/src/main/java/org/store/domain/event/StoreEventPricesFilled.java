package org.store.domain.event;

import org.store.domain.dao.PriceListDao;

public class StoreEventPricesFilled implements StoreEvent {
    
    private PriceListDao priceList;

    public StoreEventPricesFilled() {

    }

    public StoreEventPricesFilled(PriceListDao priceList) {
        this.priceList = priceList;
    }

    public PriceListDao getPriceList() {
        return this.priceList;
    }

    public void setPriceList(PriceListDao priceList) {
        this.priceList = priceList;
    }

}