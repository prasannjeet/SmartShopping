package org.store.domain.dao;

public class StoreProductDao {

    private String name;
    private String barcode;
    private String quantity;
    private Double price;
    private Boolean hasWeight;

    public StoreProductDao() {
        this.name = "";
        this.barcode = "";
        this.quantity = "";
        this.price = 0.0;
        this.hasWeight = false;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getHasWeight() {
        return this.hasWeight;
    }

    public void setHasWeight(Boolean hasWeight) {
        this.hasWeight = hasWeight;
    }
}