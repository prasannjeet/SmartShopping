package org.store.domain.service.dao;

import org.store.domain.service.model.Store;

import java.util.LinkedList;
import java.util.List;

public class StoreDao {

    private String id;
    private String userId;

    public StoreDao() {

    }

    public StoreDao(String id, Store store) {
        this.id = id;
        this.userId = store.getUserId();
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
}
