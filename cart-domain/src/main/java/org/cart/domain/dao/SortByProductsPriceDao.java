package org.cart.domain.dao;

import java.util.LinkedList;
import java.util.List;

public class SortByProductsPriceDao {

    private List<SortByProductPriceDao> products;

    public SortByProductsPriceDao() {
        this.products = new LinkedList<>();
    }

    public List<SortByProductPriceDao> getProducts() {
        return this.products;
    }

    public void setProducts(List<SortByProductPriceDao> products) {
        this.products = products;
    }
}
