package org.cart.domain.dao;

import org.cart.domain.model.Product;

import java.util.List;

public class CartResponseDao {

    private String userId;
    private List<Product> products;

    public CartResponseDao(String userId, List<Product> products) {
        this.userId = userId;
        this.products = products;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}