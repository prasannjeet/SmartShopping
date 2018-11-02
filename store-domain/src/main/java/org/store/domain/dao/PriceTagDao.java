package org.store.domain.dao;

import org.store.domain.model.PriceTag;

public class PriceTagDao {

    private String barcode;
    private String price;

    public PriceTagDao(PriceTag priceTag) {
        this.setBarcode(priceTag.getBarcode());
        this.setPrice(priceTag.getPrice());
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
