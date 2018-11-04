package org.cart.domain.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CartPriceTagDao {

    private String name;
    private String price;

    @JsonIgnore
    private Double rawPrice;

    public CartPriceTagDao() {
        this.name = "";
        this.price = "";
        this.rawPrice = 0.0;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Double getRawPrice() {
        return this.rawPrice;
    }

    public void setRawPrice(Double rawPrice) {
        this.rawPrice = rawPrice;
    }
}
