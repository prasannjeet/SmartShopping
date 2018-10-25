package org.cart.domain.service.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cart {

    @Id
    private String id;

    @NotBlank
    @Column(name = "user_id", unique = true)
    private String userId;

    public Cart() {

    }

    public Cart(String id, String userId) {
        this.id = id;
        this.userId = userId;
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

    @Override
    public String toString() {
        return "Cart { id = " + this.id + ", userId = " + this.userId + "}";
    }
}
