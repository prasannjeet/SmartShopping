package org.store.domain.dao;

import org.store.domain.model.PriceTag;
import org.store.domain.model.Store;

import java.util.LinkedList;
import java.util.List;

public class StoreDao {

    private String id;
    private String name;
    private String website;
    private String location;
    private List<PriceTagDao> priceTags;

    public StoreDao(Store store, List<PriceTag> priceTags) {
        this.setId(store.getId());
        this.setName(store.getName());
        this.setWebsite(store.getWebsite());
        this.setLocation(store.getLocation());
        this.priceTags = new LinkedList<>();
        priceTags.forEach(priceTag -> this.priceTags.add(new PriceTagDao(priceTag)));
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<PriceTagDao> getPriceTags() {
        return this.priceTags;
    }

    public void setPriceTags(List<PriceTagDao> priceTags) {
        this.priceTags = priceTags;
    }
}
