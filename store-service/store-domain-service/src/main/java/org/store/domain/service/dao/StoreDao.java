package org.store.domain.service.dao;

import org.store.domain.service.model.Store;

public class StoreDao {

    private String id;
    private String storeName;
    private String website;
    private String location;

    public StoreDao() {

    }

    public StoreDao(String id, Store store) {
        this.id = id;
        this.storeName = store.getStoreName();
        this.website = store.getWebsite();
        this.location = store.getLocation();
    }
    
    public StoreDao(Store store){
    	this.id = store.getId();
    	this.storeName = store.getStoreName();
    	this.website = store.getWebsite();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLocation() {
        return location;
    }

	public void setLocation(String location) {
        this.location = location;
    }
}
