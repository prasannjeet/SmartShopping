package org.store.domain.dao;

import java.util.LinkedList;
import java.util.List;

public class StoreCartDao {

    private String userId;
    private String storeName;
    private Double storeDistance; // distance from user
    private List<StoreProductDao> storeProductDaos;

    public StoreCartDao() {
        this.userId = "";
        this.storeName = "";
        this.storeDistance = 0.0;
        this.storeProductDaos = new LinkedList<>();
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Double getStoreDistance() {
        return this.storeDistance;
    }

    public void setStoreDistance(Double storeDistance) {
        this.storeDistance = storeDistance;
    }

    public List<StoreProductDao> getStoreProductDaos() {
        return this.storeProductDaos;
    }

    public void setStoreProductDaos(List<StoreProductDao> products) {
        this.storeProductDaos = products;
    }
}