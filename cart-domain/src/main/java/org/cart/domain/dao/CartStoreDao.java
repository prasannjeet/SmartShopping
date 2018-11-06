package org.cart.domain.dao;

import java.util.LinkedList;
import java.util.List;

public class CartStoreDao {

    private String name;
    private String distance;
    private List<CartProductDao> products;
    private String grandTotal;

    public CartStoreDao() {
        this.name = "";
        this.distance = "";
        this.products = new LinkedList<>();
        this.grandTotal = "";
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return this.distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<CartProductDao> getProducts() {
        return this.products;
    }

    public void setProducts(List<CartProductDao> products) {
        this.products = products;
    }

    public String getGrandTotal() {
        return this.grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }
}
