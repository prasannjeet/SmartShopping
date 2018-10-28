package org.cart.domain.service.dao;

import org.cart.domain.service.model.Cart;

import java.util.LinkedList;
import java.util.List;

public class CartDao {

    private String id;
    private String userId;
    private List<ProductDao> products;

    public CartDao() {

    }

    public CartDao(String id, Cart cart) {
        this.id = id;
        this.userId = cart.getUserId();
        this.products = new LinkedList<>();
    }

    public CartDao(Cart cart, List<ProductDao> products) {
        this.id = cart.getId();
        this.userId = cart.getUserId();
        this.products = products;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ProductDao> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductDao> products) {
        this.products = products;
    }
}
