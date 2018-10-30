package org.store.domain.service.dao;

import org.store.domain.service.model.Store;

import java.util.LinkedList;
import java.util.List;

public class StoreDao {

    private String id;
    private String storeName;

    public StoreDao() {

    }

    public StoreDao(String id, Store store) {
        this.id = id;
        this.storeName = store.getStoreName();
    }
    
    public StoreDao(Store store){
    	this.id = store.getId();
    	this.storeName = store.getStoreName();
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
}
