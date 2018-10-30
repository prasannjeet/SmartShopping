package org.store.domain.service.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "store")
public class Store {

    @Id
    private String id;

    @NotBlank
    @Column(name = "store_name", unique = true, nullable = false)
    private String storeName;

    public Store() {

    }

    public Store(String id, String storeName) {
        this.setId(id);
        this.setStoreName(storeName);
    }

    public Store(Store store) {
        this.setStoreName(store.storeName);
    }

    public Store(String storeName) {
        this.setStoreName(storeName);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = (id == null) ? this.id : (id.isEmpty() ? this.id : id);
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = (storeName == null) ? this.storeName : (storeName.isEmpty() ? this.storeName : storeName);
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }
}
