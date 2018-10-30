package org.store.domain.service.dao;

import org.store.domain.service.model.Product;

public class ProductDao {

    private String id;
    private String barcode;
    private String name;
    private String brand;
    private String quantity;

    public ProductDao() {

    }

    public ProductDao(Product product) {
        this.id = product.getId();
        this.barcode = product.getBarcode();
        this.name = product.getName();
        this.brand = product.getBrand();
        this.quantity = product.getQuantity();
    }

    public ProductDao(String id, Product product) {
        this.id = id;
        this.barcode = product.getBarcode();
        this.name = product.getName();
        this.brand = product.getBrand();
        this.quantity = product.getQuantity();
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
