package org.store.domain.model;

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
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String website;

    @NotBlank
    @Column(nullable = false)
    private String location;

    public Store() {

    }

    public Store(String id, Store store) throws Exception {
        this.setId(id);
        this.setName(store.name);
        this.setWebsite(store.website);
        this.setLocation(store.location);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = (id == null || id.isEmpty()) ? this.id : id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = (name == null || name.isEmpty()) ? this.name : name;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = (website == null || website.isEmpty()) ? this.website : website;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) throws Exception {
        Double.parseDouble(location);
        this.location = (location == null || location.isEmpty()) ? this.location : location;
    }
}
