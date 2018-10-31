package org.product.domain.service.event;

import org.product.domain.service.model.Product;

import java.util.ArrayList;

public class ProductEventGiveAllProducts implements ProductEvent {

    private ArrayList<Product> products;

    public ProductEventGiveAllProducts() {
    		
    }

    public ProductEventGiveAllProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public Page<Product> getProducts() {
        return this.products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}