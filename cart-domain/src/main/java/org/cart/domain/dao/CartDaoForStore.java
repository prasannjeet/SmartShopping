package org.cart.domain.dao;

import org.cart.domain.model.Product;

import java.util.LinkedList;
import java.util.List;

public class CartDaoForStore {

    private String userId;
    private String userLocation;
    private List<ProductDaoForStore> products;

    public CartDaoForStore(String userId, String userLocation, List<Product> products) {
        this.userId = userId;
        this.userLocation = userLocation;
        this.products = new LinkedList<>();
        products.forEach(prod -> this.products.add(new ProductDaoForStore(prod.getBarcode())));
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserLocation() {
        return this.userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public List<ProductDaoForStore> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductDaoForStore> products) {
        this.products = products;
    }
}
