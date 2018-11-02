package org.cart.domain.service.dao;

import org.cart.domain.service.model.Product;

import java.util.List;

public class CartDaoForEndUser {

    private String userId;
    private List<Product> products;

    public CartDaoForEndUser(String userId, List<Product> products) {
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