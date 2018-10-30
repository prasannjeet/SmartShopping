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

    @NotBlank
    @Column(name = "website", nullable = false)
    private String website;

    @NotBlank
    @Column(name = "location", nullable = false)
    private String location;
    
    public Store() {

    }

	public Store(String id, String storeName, String website, String location) {
        this.setId(id);
        this.setStoreName(storeName);
        this.setWebsite(website);
        this.setLocation(location);
    }

    public Store(Store store) {
        this.setStoreName(store.storeName);
        this.setWebsite(store.website);
        this.setLocation(store.location);
    }

    public Store(String storeName, String website, String location) {
    	this.setStoreName(storeName);
        this.setWebsite(website);
        this.setLocation(location);
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
    
    public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = (website == null) ? this.website : (website.isEmpty() ? this.website : website);
	}

	public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = (location == null) ? this.location : (location.isEmpty() ? this.location : location);
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
