package org.store.domain.service.dao;

import org.store.domain.service.model.Store;

import java.util.LinkedList;
import java.util.List;

public class StoreDao {

    private String id;
    private String userId;
    private List<ProductDao> products;

    public StoreDao() {

    }

    public StoreDao(String id, Store store) {
        this.id = id;
        this.userId = store.getUserId();
        this.products = new LinkedList<>();
    }

    public StoreDao(Store store, List<ProductDao> products) {
        this.id = store.getId();
        this.userId = store.getUserId();
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
