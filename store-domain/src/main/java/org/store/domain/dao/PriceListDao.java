package org.store.domain.dao;

import org.store.domain.model.Store;

import java.util.ArrayList;
import java.util.List;

public class PriceListDao {

    private String userId;
    private String storeName;
    private String distanceFromUser;
    private List<PriceTagDao> pricesTags;

    public PriceListDao(String userId, Store store, String distanceFromUser) {
        this.setUserId(userId);
        this.setStoreName(store.getName());
        this.setDistanceFromUser(distanceFromUser);
        pricesTags = new ArrayList<PriceTagDao>();
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

    public void addPriceTag(PriceTagDao priceTag) {
        this.pricesTags.add(priceTag);
    }
}
