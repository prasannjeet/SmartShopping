package org.cart.domain.dao;

import java.util.LinkedList;
import java.util.List;

public class SortByStoresDistanceDao {

    private List<CartStoreDao> stores;

    public SortByStoresDistanceDao() {
        this.stores = new LinkedList<>();
    }

    public List<CartStoreDao> getStores() {
        return this.stores;
    }

    public void setStores(List<CartStoreDao> stores) {
        this.stores = stores;
    }
}
