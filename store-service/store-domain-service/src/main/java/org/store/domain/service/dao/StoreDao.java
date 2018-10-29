package org.store.domain.service.dao;

import org.store.domain.service.model.Store;

public class StoreDao {
	private String id;
    private String name;
    private String website;
    

    public StoreDao() {

    }

    public StoreDao(Store store){
    	this.id = store.getId();
        this.name = store.getName();
        this.website = store.getWebsite();
    }
    
    public StoreDao(String id, Store store) {
        this.id = id;
        this.name = store.getName();
        this.website = store.getWebsite();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
}
