package org.cart.domain.dao;

import java.util.LinkedList;
import java.util.List;

public class SortByProductPriceDao {

    private List<CartPriceTagDao> prices;
    private String name;

    public SortByProductPriceDao() {
        this.name = "";
        this.prices = new LinkedList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CartPriceTagDao> getPrices() {
        return this.prices;
    }

    public void setPrices(List<CartPriceTagDao> prices) {
        this.prices = prices;
    }
}
