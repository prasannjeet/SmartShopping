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
    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;

    public Store() {

    }

    public Store(String id, String userId) {
        this.setId(id);
        this.setUserId(userId);
    }

    public Store(Store store) {
        this.setUserId(store.userId);
    }

    public Store(String userId) {
        this.setUserId(userId);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = (id == null) ? this.id : (id.isEmpty() ? this.id : id);
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = (userId == null) ? this.userId : (userId.isEmpty() ? this.userId : userId);
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
