package org.store.domain.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Id;

public class Product {

    @Id
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String price;

    @NotBlank
    private String barcode;

    @NotBlank
    private String hasWeight;

    public Product() {

    }

    public Product(String id, Product product) throws Exception {
        this.setId(id);
        this.setName(product.name);
        this.setPrice(product.price);
        this.setBarcode(product.barcode);
        this.setHasWeight(product.hasWeight);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = (id == null || id.isEmpty()) ? this.id : id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = (name == null || name.isEmpty()) ? this.name : name;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) throws Exception {
        if (Double.parseDouble(price) <= 0) {
            throw new Exception("Price must be > 0");
        }
        this.price = price;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String hasWeight() {
        return this.hasWeight;
    }

    public void setHasWeight(String hasWeight) {
        this.hasWeight = (hasWeight == null || hasWeight.isEmpty()) ? this.hasWeight : hasWeight;
    }
}
