package org.cart.domain.dao;

import org.cart.domain.model.Product;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CartDao {

    @NotBlank
    private String userId;

    @NotNull
    private Double userLocation;

    private List<Product> products;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getUserLocation() {
        return this.userLocation;
    }

    public void setUserLocation(Double userLocation) {
        this.userLocation = userLocation;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
