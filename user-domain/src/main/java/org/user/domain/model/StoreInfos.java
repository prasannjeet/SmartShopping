package org.user.domain.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Id;

public class StoreInfos {

    @Id
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String website;

    @NotBlank
    private String location;

    public StoreInfos(String name, String website, String location) {
        this.name = name;
        this.website = website;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}