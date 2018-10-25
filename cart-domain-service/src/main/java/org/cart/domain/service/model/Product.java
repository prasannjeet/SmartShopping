package org.cart.domain.service.model;

import javax.validation.constraints.NotNull;

public class Product {

    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String storeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoreId() {
        return this.storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "Product { id = " + this.id + ", name = " + this.name + ", storeId = " + this.storeId + " }";
    }
}
