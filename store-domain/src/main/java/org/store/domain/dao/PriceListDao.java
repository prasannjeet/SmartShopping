package org.store.domain.dao;

import org.store.domain.model.PriceTag;
import org.store.domain.model.Store;

import java.util.List;

public class PriceListDao {

    private String userId;
    private String storeName;
    private String distanceFromUser;
    private List<PriceTagDao> pricesTags;

    public PriceListDao(String userId, Store store, PriceTag priceTag) {
        this.setUserId(userId);
        this.setStoreName(store.getName());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDistanceFromUser() {
        return distanceFromUser;
    }

    public void setDistanceFromUser(String distanceFromUser) {
        this.distanceFromUser = distanceFromUser;
    }

    public List<PriceTagDao> getPricesTags() {
        return pricesTags;
    }

    public void setPricesTags(List<PriceTagDao> pricesTags) {
        this.pricesTags = pricesTags;
    }
}
