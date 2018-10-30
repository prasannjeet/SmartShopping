package org.store.domain.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "store")
public class Store {
    @Id
    private String id;
    
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;
    
    @NotBlank
    @Column(name = "website", nullable = false)
    private String website;

    public Store() {

    }

    public Store(String id, String name, String website) {
        this.setId(id);
        this.setName(name);
        this.setWebsite(website);
    }
    
    public Store(Store store) {
    	this.setId(id);
        this.setName(name);
        this.setWebsite(website);
	}

	public String getId() {
        return this.id;
    }

    public void setId(String id) {  
        this.id = (id == null) ? this.id : (id.isEmpty() ? this.id : id);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = (name == null) ? this.name : (name.isEmpty() ? this.name : name);
    }
    
    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = (website == null) ? this.website : (website.isEmpty() ? this.website : website);
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
