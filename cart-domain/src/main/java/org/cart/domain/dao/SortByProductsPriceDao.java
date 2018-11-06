package org.cart.domain.dao;

import java.util.LinkedList;
import java.util.List;

public class SortByProductsPriceDao {

    private List<SortByProductsPriceDao> products;

    public SortByProductsPriceDao() {
        this.products = new LinkedList<>();
    }

    public List<SortByProductsPriceDao> getProducts() {
        return this.products;
    }

    public void setProducts(List<SortByProductsPriceDao> products) {
        this.products = products;
    }
}
