package org.cart.domain.dao;

public class ProductDaoForStore {

    private String barcode;

    public ProductDaoForStore(String barcode) {
        this.barcode = barcode;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
